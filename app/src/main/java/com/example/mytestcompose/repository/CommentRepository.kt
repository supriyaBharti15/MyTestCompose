package com.example.mytestcompose.repository

import com.example.mytestcompose.api.ApiServices
import com.example.mytestcompose.model.CommentResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CommentRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getComment():Flow<List<CommentResponseItem>> = flow {
        val responseItem = apiServices.getComment()
        if (responseItem.isSuccessful){
            responseItem.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)
}