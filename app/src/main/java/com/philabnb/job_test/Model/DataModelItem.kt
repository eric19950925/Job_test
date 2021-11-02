package com.philabnb.job_test.Model


import com.google.gson.annotations.SerializedName

data class DataModelItem(
    @SerializedName("apod_site")
    val apodSite: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("hdurl")
    val hdurl: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)