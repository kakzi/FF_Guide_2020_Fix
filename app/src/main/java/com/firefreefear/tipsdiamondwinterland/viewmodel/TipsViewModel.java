package com.firefreefear.tipsdiamondwinterland.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firefreefear.tipsdiamondwinterland.model.TipsModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.firefreefear.tipsdiamondwinterland.helper.Utils.BASE_URL;
import static com.firefreefear.tipsdiamondwinterland.helper.Utils.GET_TIPS;

public class TipsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TipsModel>> listData = new MutableLiveData<>();

    public void setTips (final Context context){

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TipsModel> listItem = new ArrayList<>();

        String url = BASE_URL + GET_TIPS;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responsObject = new JSONObject(result);
                    JSONArray list = responsObject.getJSONArray("tips");
                    for (int i=0; i<list.length(); i++){
                        JSONObject tips = list.getJSONObject(i);
                        TipsModel tipsModelList = new TipsModel(tips);
                        listItem.add(tipsModelList);
                    }
                    listData.postValue(listItem);

                } catch (JSONException e) {
                    Toast.makeText(context, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                    Log.d("Exception", e.getMessage());
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    public LiveData<ArrayList<TipsModel>> getTips() {
        return listData;
    }

}
