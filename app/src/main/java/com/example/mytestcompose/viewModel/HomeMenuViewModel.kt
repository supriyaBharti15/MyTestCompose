package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import com.example.mytestcompose.utils.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeMenuViewModel @Inject constructor() : ViewModel() {

    fun getData(): List<String> {
        return listOf(
            Destination.TODO_SCREEN,
            Destination.PRODUCT_SCREEN,
            Destination.COMMENT_SCREEN,
            Destination.QUOTES_SCREEN,
            Destination.STATE_HOSTING_SCREEN,
            Destination.RECIPES_SCREEN,
            Destination.PHOTO_SCREEN

        )
    }
}