package com.example.practicemvi.data.api


import com.example.practicemvi.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getItems(): List<Item>
}