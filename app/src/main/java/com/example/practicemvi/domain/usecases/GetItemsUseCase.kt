package com.example.practicemvi.domain.usecases

import com.example.practicemvi.data.model.Item
import com.example.practicemvi.domain.repository.ItemRepository

class GetItemsUseCase(private val itemRepository: ItemRepository) {
    suspend fun execute(): List<Item> {
        return itemRepository.getItems()
    }
}