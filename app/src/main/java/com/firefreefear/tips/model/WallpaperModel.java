package com.firefreefear.tips.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class WallpaperModel implements Parcelable {

    private String id;
    private String title;
    private String copyright;
    private String image_url;


    public WallpaperModel(String id, String title, String copyright, String image_url) {
        this.id = id;
        this.title = title;
        this.copyright = copyright;
        this.image_url = image_url;
    }

    public WallpaperModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public WallpaperModel(JSONObject object){
        try{
            String id = object.getString("id");
            String title = object.getString("title");
            String copyright = object.getString("copyright");
            String image_url = object.getString("image_url");

            this.id = id;
            this.title = title;
            this.copyright = copyright;
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
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.copyright);
        dest.writeString(this.image_url);
    }

    protected WallpaperModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.copyright = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<WallpaperModel> CREATOR = new Parcelable.Creator<WallpaperModel>() {
        @Override
        public WallpaperModel createFromParcel(Parcel source) {
            return new WallpaperModel(source);
        }

        @Override
        public WallpaperModel[] newArray(int size) {
            return new WallpaperModel[size];
        }
    };
}
