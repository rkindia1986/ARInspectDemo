package com.optimumus.arinspectdemo.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by INSPRION on 31-05-2019.
 */

public class FactDataList {
    @SerializedName("rows")
    public ArrayList<FactData> factDataArrayList;

    @SerializedName("title")
    public String title;

}
