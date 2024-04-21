package com.example.myapplication.api

import com.example.myapplication.model.EventDataModel
import retrofit2.Call
import retrofit2.http.GET


interface DAZNAPI {
    @GET("/getEvents")
    fun getEvents(): Call<List<EventDataModel>>

    @GET("/getSchedule")
    fun getSchedule(): Call<List<EventDataModel>>
}