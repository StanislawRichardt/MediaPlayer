package com.example.myapplication.ui.eventscreen

import com.example.myapplication.api.EventsCall
import com.example.myapplication.api.ResponseCallback
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.EventDataModel
import com.example.myapplication.ui.other.UIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

open class EventListViewModel:UIViewModel() {
    val events = MutableStateFlow<MutableList<UIModel>>(mutableListOf())
    var rawData: MutableList<UIModel> = mutableListOf()

    override fun fetchData(){
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
}