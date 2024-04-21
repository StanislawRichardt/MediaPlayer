package com.example.myapplication.api

import com.example.myapplication.model.EventDataModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

interface DAZNAPICalls: Callback<List<EventDataModel>> {

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


    override fun onFailure(call: Call<List<EventDataModel>>, t: Throwable) {
        t.printStackTrace()
    }

}