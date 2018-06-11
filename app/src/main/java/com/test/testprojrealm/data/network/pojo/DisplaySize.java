package com.test.testprojrealm.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DisplaySize {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("is_watermarked")
    @Expose
    private boolean isWatermarked;
    @SerializedName("name")
    @Expose
    private String name;


    public boolean isIsWatermarked() {
        return isWatermarked;
    }

    public void setIsWatermarked(boolean isWatermarked) {
        this.isWatermarked = isWatermarked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
