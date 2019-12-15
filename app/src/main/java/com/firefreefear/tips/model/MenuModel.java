package com.firefreefear.tips.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuModel implements Parcelable {

    private int id;
    private String title_menu;
    private String sub_title;
    private String image_url;

    public MenuModel() {
    }

    public MenuModel(int id, String title_menu, String sub_title, String image_url) {
        this.id = id;
        this.title_menu = title_menu;
        this.sub_title = sub_title;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle_menu() {
        return title_menu;
    }

    public void setTitle_menu(String title_menu) {
        this.title_menu = title_menu;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static Creator<MenuModel> getCREATOR() {
        return CREATOR;
    }

    public MenuModel(JSONObject object){
        try{
            this.id = object.getInt("id");
            String title_menu = object.getString("title_menu");
            String sub_title = object.getString("sub_title");
            String image_url = object.getString("image_url");

            this.title_menu = title_menu;
            this.sub_title = sub_title;
            this.image_url = image_url;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title_menu);
        dest.writeString(this.sub_title);
        dest.writeString(this.image_url);
    }

    protected MenuModel(Parcel in) {
        this.id = in.readInt();
        this.title_menu = in.readString();
        this.sub_title = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<MenuModel> CREATOR = new Parcelable.Creator<MenuModel>() {
        @Override
        public MenuModel createFromParcel(Parcel source) {
            return new MenuModel(source);
        }

        @Override
        public MenuModel[] newArray(int size) {
            return new MenuModel[size];
        }
    };
}
