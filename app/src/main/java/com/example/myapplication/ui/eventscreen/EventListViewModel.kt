package com.example.myapplication.ui.eventscreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.EventsCall
import com.example.myapplication.api.ImageProvider
import com.example.myapplication.api.ResponseCallback
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.VideoModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.net.URL

class EventListViewModel:ViewModel() {
    val streams = MutableStateFlow<MutableList<UIModel>>(mutableListOf())
    var rawData: MutableList<UIModel> = mutableListOf()

    fun fetchData(){
        EventsCall.start(object: ResponseCallback{
            override suspend fun onResponseLoaded(response: List<VideoModel>) {
                for(event in response){
                    rawData.add(UIModel(
                        event.title,
                        event.subtitle,
                        event.date,
                        convertImageURLIntoBitmap(event.imageUrl),
                        event.videoUrl
                    )
                    )
                }
                streams.emit(rawData)
                rawData = mutableListOf()
            }
        })
    }

    fun convertImageURLIntoBitmap(url: String?): Bitmap? {
        val bitmap: Bitmap?
        val connection = URL(url).openConnection()
        connection.connect()
        val inputStream = connection.getInputStream()
        bitmap = BitmapFactory.decodeStream(inputStream)
        return bitmap
    }
}