package com.example.myapplication.ui.schedulescreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.ImageProvider
import com.example.myapplication.api.ResponseCallback
import com.example.myapplication.api.ScheduleCall
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.VideoModel
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ScheduleListViewModel: ViewModel() {
    val streams =  MutableStateFlow<MutableList<UIModel>>(mutableListOf())
    var rawData: MutableList<UIModel> = mutableListOf()

    fun fetchData(){
        ScheduleCall.start(object: ResponseCallback{
            override suspend fun onResponseLoaded(response: List<VideoModel>) {
                for(event in response){
                    rawData.add(UIModel(
                        event.title,
                        event.subtitle,
                        event.date,
                        null,
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
        val connection: Retrofit? = url?.let { Retrofit.Builder().baseUrl(it).build() }
        var bitmap: Bitmap? = null
        connection?.create(ImageProvider::class.java)?.downloadImage(url)?.enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        val inputStream = response.body()?.byteStream()
                        bitmap = BitmapFactory.decodeStream(inputStream)

                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        return bitmap
    }
}