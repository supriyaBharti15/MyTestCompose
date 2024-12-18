package com.example.mytestcompose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestcompose.model.recipes.Recipe

class RecipesDetailSharedViewModel:ViewModel() {
    private val _details=MutableLiveData<Recipe>()
    val detail:LiveData<Recipe>
        get() = _details
    fun setDetail(data:Recipe){
        _details.value=data
    }
}