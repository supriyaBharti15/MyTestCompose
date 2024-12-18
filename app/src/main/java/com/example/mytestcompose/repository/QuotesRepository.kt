package com.example.mytestcompose.repository

import com.example.mytestcompose.api.ApiServicesNew
import com.example.mytestcompose.model.quotes.QuotesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class QuotesRepository @Inject constructor(private val apiServicesNew: ApiServicesNew) {
    suspend fun getQuotes():Flow<QuotesResponse> = flow {
        val response=apiServicesNew.getQuotes()
        if (response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

}