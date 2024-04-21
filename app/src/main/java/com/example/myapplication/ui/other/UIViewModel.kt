package com.example.myapplication.ui.other

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

abstract class UIViewModel:ViewModel() {
        abstract fun fetchData()

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