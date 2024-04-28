package com.example.retrofit

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import android.widget.Button
import androidx.fragment.app.viewModels
import kotlinx.coroutines.launch


class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = requireActivity().findViewById<ImageView>(R.id.cat_img)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            lifecycleScope.launch {
                val cat = RetrofitInstance.searchImageApi.getCatImageList()
                imageView.load(cat.first().url)
            }
        }
    }
}