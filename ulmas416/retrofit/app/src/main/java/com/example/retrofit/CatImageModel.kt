package com.example.retrofit

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
data class CatImageModel(
    @field:Json(name = "url") val url: String
)
