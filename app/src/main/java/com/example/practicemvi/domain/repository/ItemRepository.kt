package com.example.practicemvi.domain.repository

import com.example.practicemvi.data.model.Item

interface ItemRepository {
    suspend fun getItems(): List<Item>
}