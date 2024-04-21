package com.example.myapplication

import com.example.myapplication.ui.eventscreen.EventListViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.mockito.Mockito.mock
import java.lang.RuntimeException

class EventListViewModelTest {

    private val eventListViewModel: EventListViewModel = mock(EventListViewModel::class.java)

    @Test
    fun testConvertImageURLIntoBitmapWithMalformedURLException(){
        val response = eventListViewModel.convertImageURLIntoBitmap("not_an_url")
        assertEquals(null, response)
    }

    @Test
    fun testConvertImageURLIntoBitmap(){
        val exception = assertThrows(RuntimeException::class.java) {
            eventListViewModel.convertImageURLIntoBitmap("https://google.com/")
        }
        assertEquals("Method decodeStream in android.graphics.BitmapFactory not mocked. See https://developer.android.com/r/studio-ui/build/not-mocked for details.", exception.message)
    }
}