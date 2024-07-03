package com.example.practicemvi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicemvi.domain.usecases.GetItemsUseCase
import com.example.practicemvi.presentation.intent.ItemIntent
import com.example.practicemvi.presentation.state.ItemViewState
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val getItemsUseCase: GetItemsUseCase) : ViewModel() {
    private val _state = MutableLiveData<ItemViewState>()
    val state: LiveData<ItemViewState> get() = _state

    fun processIntent(intent: ItemIntent) {
        when (intent) {
            is ItemIntent.FetchItems -> fetchItems()
        }
    }

    private fun fetchItems() {
        _state.value = ItemViewState.Loading
        viewModelScope.launch {
            try {
                val items = getItemsUseCase.execute()
                _state.value = ItemViewState.Success(items)
            } catch (e: Exception) {
                Log.e("TAG", "fetchItems: $e", )
                _state.value = ItemViewState.Error("Failed to load items")
            }
        }
    }
}