package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.recipes.Recipe
import com.example.mytestcompose.model.recipes.RecipesResponse
import com.example.mytestcompose.repository.RecipesRepository
import com.example.mytestcompose.utils.StateController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(private val repository: RecipesRepository):ViewModel() {
    private val _recipes=MutableStateFlow<StateController<RecipesResponse>>(StateController.Empty)
    val recipes:StateFlow<StateController<RecipesResponse>>
        get() = _recipes

    fun getRecipes(){
        _recipes.value=StateController.Loading
        viewModelScope.launch {
            try {
                repository.getRecipes().collect{
                    _recipes.value=StateController.Success(it)
                }
            }catch (e:Exception){
                _recipes.value=StateController.Failure(e.message.toString())
            }
        }
    }
}