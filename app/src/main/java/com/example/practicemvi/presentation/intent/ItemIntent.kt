package com.example.practicemvi.presentation.intent

sealed class ItemIntent {
    object FetchItems : ItemIntent()
}