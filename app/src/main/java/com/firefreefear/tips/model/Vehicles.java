package com.firefreefear.tips.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Vehicles implements Parcelable {
    private String id;
    private String name;
    private String image_url;
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Vehicles() {
    }

    public Vehicles(JSONObject object){
        try{
            String id = object.getString("id");
            String name = object.getString("name");
            String image_url = object.getString("image_url");
            String desc = object.getString("desc");

            this.id = id;
            this.name = name;
            this.image_url = image_url;
            this.desc = desc;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Vehicles(String id, String name, String image_url, String desc) {
        this.id = id;
        this.name = name;
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
        dest.writeString(this.name);
        dest.writeString(this.image_url);
        dest.writeString(this.desc);
    }

    protected Vehicles(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.image_url = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<Vehicles> CREATOR = new Parcelable.Creator<Vehicles>() {
        @Override
        public Vehicles createFromParcel(Parcel source) {
            return new Vehicles(source);
        }

        @Override
        public Vehicles[] newArray(int size) {
            return new Vehicles[size];
        }
    };
}
