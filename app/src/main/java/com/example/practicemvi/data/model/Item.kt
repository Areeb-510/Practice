package com.example.practicemvi.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("title") val name : String,
    @SerializedName("image") val imageUrl : String
)
