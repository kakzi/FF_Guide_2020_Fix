package com.firefreefear.tips.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class TipsModel implements Parcelable {

    private String id;
    private String title;
    private String sub_title;
    private String image_url;
    private String desc;

    public TipsModel(String id, String title, String sub_title, String image_url, String desc) {
        this.id = id;
        this.title = title;
        this.sub_title = sub_title;
        this.image_url = image_url;
        this.desc = desc;
    }

    public TipsModel() {
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public TipsModel(JSONObject object) {
        try {
            String id = object.getString("id");
            String tittle = object.getString("tittle");
            String sub_title = object.getString("sub_title");
            String image_url = object.getString("image_url");
            String desc = object.getString("desc");


            this.id = id;
            this.title = tittle;
            this.sub_title = sub_title;
            this.image_url = image_url;
            this.desc = desc;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.sub_title);
        dest.writeString(this.image_url);
        dest.writeString(this.desc);
    }

    protected TipsModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.sub_title = in.readString();
        this.image_url = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<TipsModel> CREATOR = new Parcelable.Creator<TipsModel>() {
        @Override
        public TipsModel createFromParcel(Parcel source) {
            return new TipsModel(source);
        }

        @Override
        public TipsModel[] newArray(int size) {
            return new TipsModel[size];
        }
    };
}
