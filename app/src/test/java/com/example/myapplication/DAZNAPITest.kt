package com.example.myapplication

import com.example.myapplication.api.DAZNAPI
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class DAZNAPITest {

    private val mockAPI: DAZNAPI = mock(DAZNAPI::class.java)

    @Test
    fun testGetEvents(){
        mockAPI.getEvents()
        verify(mockAPI).getEvents()
    }

    @Test
    fun testGetSchedule(){
        mockAPI.getSchedule()
        verify(mockAPI).getSchedule()
    }

}