package com.example.mytestcompose.repository

import androidx.compose.foundation.text.selection.DisableSelection
import com.example.mytestcompose.api.ApiServicesNew
import com.example.mytestcompose.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiServicesNew: ApiServicesNew) {
    suspend fun getProduct(offset:Int,limit:Int):Flow<ProductResponse> = flow {
        val response=apiServicesNew.getProduct(offset,limit)
        if (response.isSuccessful){
            response.body()?.let {
                emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)
}