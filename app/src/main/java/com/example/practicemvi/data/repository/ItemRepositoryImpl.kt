package com.example.practicemvi.data.repository

import com.example.practicemvi.data.api.ApiService
import com.example.practicemvi.data.model.Item
import com.example.practicemvi.domain.repository.ItemRepository


class ItemRepositoryImpl(private val apiService: ApiService) : ItemRepository {
    override suspend fun getItems(): List<Item> {
        return apiService.getItems()
    }
}