package com.firefreefear.tips.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firefreefear.tips.model.MenuModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.firefreefear.tips.helper.Utils.BASE_URL;
import static com.firefreefear.tips.helper.Utils.GET_MENU;

public class MenuViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MenuModel>> listMenu = new MutableLiveData<>();

    public void setMenu(final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MenuModel> listItem = new ArrayList<>();

        String url = BASE_URL + GET_MENU;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responsObject = new JSONObject(result);
                    JSONArray list = responsObject.getJSONArray("menu");
                    for (int i=0; i<list.length(); i++){
                        JSONObject menu = list.getJSONObject(i);
                        MenuModel menuModelList = new MenuModel(menu);
                        listItem.add(menuModelList);
                    }
                    listMenu.postValue(listItem);

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


    public LiveData<ArrayList<MenuModel>> getMenu() {
        return listMenu;
    }

}
