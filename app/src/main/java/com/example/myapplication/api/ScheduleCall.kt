package com.example.myapplication.api

import com.example.myapplication.model.EventDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object ScheduleCall: DAZNAPICalls {

    private var callback: ResponseCallback? = null

    fun start(callback: ResponseCallback){
        this.callback = callback
        val scheduleCall: Call<List<EventDataModel>> = daznAPI.getSchedule()
        scheduleCall.enqueue(this)
    }

    override fun onResponse(call: Call<List<EventDataModel>>, response: Response<List<EventDataModel>>) {
        CoroutineScope(Dispatchers.IO).launch {
            callback?.onResponseLoaded(response.body() ?: listOf())
        }
    }
}