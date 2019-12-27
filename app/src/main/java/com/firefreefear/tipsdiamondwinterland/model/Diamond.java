package com.firefreefear.tipsdiamondwinterland.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Diamond implements Parcelable {

    private String id;
    private String title;
    private String image_url;
    private String desc;

    public Diamond(JSONObject object){
        try{
            String id = object.getString("id");
            String title = object.getString("title");
            String image_url = object.getString("sub_tips");
            String desc = object.getString("desc");

            this.id = id;
            this.title = title;
            this.image_url = image_url;
            this.desc = desc;
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Diamond() {
    }

    public Diamond(String id, String title, String image_url, String desc) {
        this.id = id;
        this.title = title;
        this.image_url = image_url;
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.image_url);
        dest.writeString(this.desc);
    }

    protected Diamond(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.image_url = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Diamond> CREATOR = new Parcelable.Creator<Diamond>() {
        @Override
        public Diamond createFromParcel(Parcel source) {
            return new Diamond(source);
        }

        @Override
        public Diamond[] newArray(int size) {
            return new Diamond[size];
        }
    };
}
