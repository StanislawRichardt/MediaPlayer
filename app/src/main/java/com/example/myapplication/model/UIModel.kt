package com.example.myapplication.model

import android.graphics.Bitmap

data class UIModel(
    val title: String? = null,
    val subtitle: String? = null,
    val date: String? = null,
    val image: Bitmap? = null,
    val videoUrl: String? = null
)