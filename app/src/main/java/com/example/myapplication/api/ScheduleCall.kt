package com.example.myapplication.api

import android.util.Log
import com.example.myapplication.model.VideoModel
import retrofit2.Call
import retrofit2.Response

object ScheduleCall: APICalls {

    private var callback: ResponseCallback? = null

    fun start(callback: ResponseCallback){
        this.callback = callback
        val scheduleCall: Call<List<VideoModel>> = daznAPI.getSchedule()
        scheduleCall.enqueue(this)
    }

    override fun onResponse(call: Call<List<VideoModel>>, response: Response<List<VideoModel>>) {
        callback?.onResponseLoaded(response.body() ?: listOf())
    }
}