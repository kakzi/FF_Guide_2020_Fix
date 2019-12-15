package com.firefreefear.tips.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoModel implements Parcelable {
    private String id;
    private String title;
    private String url_tumb;
    private String url_video;
    private String desc;

    public VideoModel(JSONObject object){
        try{
            String id = object.getString("id");
            String title = object.getString("title");
            String url_tumb = object.getString("url_tumb");
            String url_video = object.getString("url_video");
            String desc = object.getString("desc");

            this.id = id;
            this.title = title;
            this.url_tumb = url_tumb;
            this.url_video = url_video;
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

    public String getUrl_tumb() {
        return url_tumb;
    }

    public void setUrl_tumb(String url_tumb) {
        this.url_tumb = url_tumb;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public VideoModel() {
    }

    public VideoModel(String id, String title, String url_tumb, String url_video, String desc) {
        this.id = id;
        this.title = title;
        this.url_tumb = url_tumb;
        this.url_video = url_video;
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
        dest.writeString(this.url_tumb);
        dest.writeString(this.url_video);
        dest.writeString(this.desc);
    }

    protected VideoModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.url_tumb = in.readString();
        this.url_video = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<VideoModel> CREATOR = new Parcelable.Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel source) {
            return new VideoModel(source);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };
}
