package com.example.imagessubredditviewer.apicall

import com.example.imagessubredditviewer.model.ImageListResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("/api/v1/images/latest")
    fun doGetImageList(): Call<ImageListResponse?>?
}