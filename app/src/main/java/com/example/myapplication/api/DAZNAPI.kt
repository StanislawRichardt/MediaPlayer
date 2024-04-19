package com.example.myapplication.api

import com.example.myapplication.model.VideoModel
import retrofit2.Call
import retrofit2.http.GET


interface DAZNAPI {
    @GET("/getEvents")
    fun getEvents(): Call<List<VideoModel>>

    @GET("/getSchedule")
    fun getSchedule(): Call<List<VideoModel>>
}