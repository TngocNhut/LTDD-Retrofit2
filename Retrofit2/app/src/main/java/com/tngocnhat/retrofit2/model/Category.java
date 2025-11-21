package com.tngocnhat.retrofit2.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("images")
    private String images;

    @SerializedName("description")
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;  //
    }

    public String getImages() {
        return images; //
    }

    public String getDescription() {
        return description;
    }
}
