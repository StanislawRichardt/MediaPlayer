package com.example.myapplication.api

import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.graphics.drawable.toDrawable
import com.example.myapplication.model.VideoModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EventsCall:APICalls {

    private var callback: ResponseCallback? = null

    fun start(callback: ResponseCallback){
        this.callback = callback
        val eventsCall: Call<List<VideoModel>> = daznAPI.getEvents()
        eventsCall.enqueue(this)
    }

    override fun onResponse(call: Call<List<VideoModel>>, response: Response<List<VideoModel>>) {
        callback?.onResponseLoaded(response.body() ?: listOf())
    }
}