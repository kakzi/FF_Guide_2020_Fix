package com.firefreefear.tipsdiamondwinterland.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firefreefear.tipsdiamondwinterland.model.WallpaperModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.firefreefear.tipsdiamondwinterland.helper.Utils.BASE_URL;
import static com.firefreefear.tipsdiamondwinterland.helper.Utils.GET_WALLPAPER;

public class WallpaperViewModel extends ViewModel {

    private MutableLiveData<ArrayList<WallpaperModel>> listData = new MutableLiveData<>();
    public void setWallpaper(final Context context){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<WallpaperModel> listItem = new ArrayList<>();

        String url = BASE_URL + GET_WALLPAPER;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("wallpaper");
                    for (int i=0; i<list.length(); i++){
                        JSONObject wallpaper = list.getJSONObject(i);
                        WallpaperModel wallpaperModel = new WallpaperModel(wallpaper);
                        listItem.add(wallpaperModel);
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
    public LiveData<ArrayList<WallpaperModel>> getWallpaper() {
        return listData;
    }

}
