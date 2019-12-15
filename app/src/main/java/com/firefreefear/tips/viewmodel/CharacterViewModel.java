package com.firefreefear.tips.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.firefreefear.tips.model.Character;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.firefreefear.tips.helper.Utils.BASE_URL;
import static com.firefreefear.tips.helper.Utils.GET_CHARACTER;

public class CharacterViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Character>> listData = new MutableLiveData<>();

    public void setCharacter(final Context context){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Character> listItem = new ArrayList<>();

        String url = BASE_URL + GET_CHARACTER;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray list = jsonObject.getJSONArray("character");
                    for (int i=0; i<list.length(); i++){
                        JSONObject character = list.getJSONObject(i);
                        Character characterModel = new Character(character);
                        listItem.add(characterModel);
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
    public LiveData<ArrayList<Character>> getCharacter() {
        return listData;
    }
}
