package com.example.mytestcompose.repository

import com.example.mytestcompose.api.ApiServices
import com.example.mytestcompose.model.TodoResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodoRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getTodo():Flow<List<TodoResponseItem>> = flow {
        val response = apiServices.getTodo()
        if(response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)
}