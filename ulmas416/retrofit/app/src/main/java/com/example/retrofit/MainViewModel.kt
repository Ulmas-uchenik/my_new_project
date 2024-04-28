package com.example.retrofit

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.load
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    fun imageLoad(image: ImageView) {
        viewModelScope.launch {
            val cat = RetrofitInstance.searchImageApi.getCatImageList()
            image.load(cat.first().url)
        }
    }
}