package com.firefreefear.tips.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firefreefear.tips.model.Diamond;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.firefreefear.tips.helper.Utils.BASE_URL;
import static com.firefreefear.tips.helper.Utils.GET_DIAMOND;

public class DiamondViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Diamond>> listData = new MutableLiveData<>();

    public void setDiamond(final Context context){

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Diamond> listItem = new ArrayList<>();

        String url = BASE_URL + GET_DIAMOND;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("diamond");
                    for (int i=0; i<list.length(); i++){
                        JSONObject diamond = list.getJSONObject(i);
                        Diamond diamonds = new Diamond(diamond);
                        listItem.add(diamonds);
                    }
                    listData.postValue(listItem);

                } catch (JSONException error) {
                    error.printStackTrace();
                    Toast.makeText(context, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                    Log.d("Exception", error.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, "Please check your Internet Connection!", Toast.LENGTH_SHORT).show();
                Log.d("Exception", error.getMessage());

            }
        });

    }

    public LiveData<ArrayList<Diamond>> getDiamond() {
        return listData;
    }

}
