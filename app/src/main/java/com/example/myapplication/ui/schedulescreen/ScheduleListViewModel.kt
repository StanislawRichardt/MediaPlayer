package com.example.myapplication.ui.schedulescreen

import com.example.myapplication.api.ResponseCallback
import com.example.myapplication.api.ScheduleCall
import com.example.myapplication.model.UIModel
import com.example.myapplication.model.EventDataModel
import com.example.myapplication.ui.other.UIViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ScheduleListViewModel: UIViewModel() {
    val streams =  MutableStateFlow<MutableList<UIModel>>(mutableListOf())
    var rawData: MutableList<UIModel> = mutableListOf()

    override fun fetchData(){
        ScheduleCall.start(object: ResponseCallback{
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
                streams.emit(rawData)
                rawData = mutableListOf()
            }
        })
    }
}