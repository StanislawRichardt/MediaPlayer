package com.example.myapplication.api

import com.example.myapplication.model.EventDataModel

interface ResponseCallback {
    suspend fun onResponseLoaded(response: List<EventDataModel>)
}