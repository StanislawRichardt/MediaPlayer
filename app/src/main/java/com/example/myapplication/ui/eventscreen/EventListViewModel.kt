package com.example.myapplication.ui.eventscreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import com.example.myapplication.api.EventsCall
import com.example.myapplication.api.ResponseCallback
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.EventDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class EventListViewModel:ViewModel() {
    val events = MutableStateFlow<MutableList<UIModel>>(mutableListOf())
    var rawData: MutableList<UIModel> = mutableListOf()

    fun fetchData(){
        EventsCall.start(object: ResponseCallback{
            override suspend fun onResponseLoaded(response: List<EventDataModel>) {
                for(event in response){
                    rawData.add(UIModel(
                        event.title,
                        event.subtitle,
                        beautifyDate(event.date),
                        convertImageURLIntoBitmap(event.imageUrl),
                        event.videoUrl
                    )
                    )
                }
                rawData = rawData.sortedBy { video -> video.date }.toMutableList()
                events.emit(rawData)
                rawData = mutableListOf()
            }
        })
    }

    fun convertImageURLIntoBitmap(url: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val connection = URL(url).openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
        }catch(e: MalformedURLException){
            e.printStackTrace()
        }
        return bitmap
    }

    fun beautifyDate(date: String?): String?{
        val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        val currentDate = LocalDate.now()
        val formattedTime = parsedDate.format(DateTimeFormatter.ofPattern("HH:mm"))

        return when (parsedDate.toLocalDate()){
            currentDate -> "Today, $formattedTime"
            currentDate.minusDays(1) -> "Yesterday, $formattedTime"
            currentDate.plusDays(1) -> "Tomorrow, $formattedTime"
            else -> parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }
    }
}