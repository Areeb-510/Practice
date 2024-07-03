package com.example.practicemvi.presentation.state

import com.example.practicemvi.data.model.Item

sealed class ItemViewState {
    object Loading : ItemViewState()
    data class Success(val items: List<Item>) : ItemViewState()
    data class Error(val message: String) : ItemViewState()
}