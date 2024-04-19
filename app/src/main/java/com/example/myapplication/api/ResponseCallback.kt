package com.example.myapplication.api

import com.example.myapplication.model.VideoModel

interface ResponseCallback {
    suspend fun onResponseLoaded(response: List<VideoModel>)
}