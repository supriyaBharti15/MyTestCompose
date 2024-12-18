package com.example.mytestcompose.api

import com.example.mytestcompose.model.ProductResponse
import com.example.mytestcompose.model.TodoResponseItem
import com.example.mytestcompose.model.quotes.QuotesResponse
import com.example.mytestcompose.model.recipes.Recipe
import com.example.mytestcompose.model.recipes.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicesNew {

    //https://dummyjson.com/products?offset=0&limit=10
    @GET("products")
    suspend fun getProduct(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<ProductResponse>

    @GET("recipes")
    suspend fun getRecipes(): Response<RecipesResponse>

    @GET("quotes")
    suspend fun getQuotes():Response<QuotesResponse>
}