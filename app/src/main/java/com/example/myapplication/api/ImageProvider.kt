package com.example.myapplication.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageProvider {
    @GET
    fun downloadImage(@Url fileUrl: String): Call<ResponseBody>
}