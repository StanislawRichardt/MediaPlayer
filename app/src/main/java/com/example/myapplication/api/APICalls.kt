package com.example.myapplication.api

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.myapplication.model.VideoModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

interface APICalls: Callback<List<VideoModel>> {

    val gson: Gson
        get() = GsonBuilder().setLenient().create()

    val okHTTPClient: OkHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build()

    val connection: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net/")
            .client(okHTTPClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

    val daznAPI:DAZNAPI
        get() = connection.create(DAZNAPI::class.java)


    override fun onFailure(call: Call<List<VideoModel>>, t: Throwable) {
        t.printStackTrace()
    }

}