package com.example.mytestcompose.repository

import com.example.mytestcompose.api.ApiServicesNew
import com.example.mytestcompose.model.recipes.Recipe
import com.example.mytestcompose.model.recipes.RecipesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val apiServicesNew: ApiServicesNew) {
    suspend fun getRecipes():Flow<RecipesResponse> = flow {
        val response=apiServicesNew.getRecipes()
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)
}