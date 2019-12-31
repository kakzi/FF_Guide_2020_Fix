package com.firefreefear.tipsdiamondwinterland.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class WaeponModel implements Parcelable {

    private String id;
    private String name_waepon;
    private String url_detail;
    private String image_url;
    private String desc;

    public WaeponModel() {
    }

    public WaeponModel(String id, String name_waepon, String url_detail, String image_url, String desc) {
        this.id = id;
        this.name_waepon = name_waepon;
        this.url_detail = url_detail;
        this.image_url = image_url;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_waepon() {
        return name_waepon;
    }

    public void setName_waepon(String name_waepon) {
        this.name_waepon = name_waepon;
    }

    public String getUrl_detail() {
        return url_detail;
    }

    public void setUrl_detail(String url_detail) {
        this.url_detail = url_detail;
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

    public WaeponModel(JSONObject object) {

        try {
            String id = object.getString("id");
            String name_waepon = object.getString("name_waepon");
            String url_detail = object.getString("url_detail");
            String image_url = object.getString("image_url");
            String desc = object.getString("desc");

            this.id = id;
            this.name_waepon = name_waepon;
            this.url_detail = url_detail;
            this.image_url = image_url;
            this.desc = desc;

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
        dest.writeString(this.name_waepon);
        dest.writeString(this.url_detail);
        dest.writeString(this.image_url);
        dest.writeString(this.desc);
    }

    protected WaeponModel(Parcel in) {
        this.id = in.readString();
        this.name_waepon = in.readString();
        this.url_detail = in.readString();
        this.image_url = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<WaeponModel> CREATOR = new Parcelable.Creator<WaeponModel>() {
        @Override
        public WaeponModel createFromParcel(Parcel source) {
            return new WaeponModel(source);
        }

        @Override
        public WaeponModel[] newArray(int size) {
            return new WaeponModel[size];
        }
    };
}
