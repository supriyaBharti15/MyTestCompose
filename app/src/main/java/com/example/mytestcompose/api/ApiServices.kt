package com.example.mytestcompose.api

import com.example.mytestcompose.model.CommentResponseItem
import com.example.mytestcompose.model.TodoResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("todos")
    suspend fun getTodo(): Response<List<TodoResponseItem>>

    @GET("comments")
    suspend fun getComment():Response<List<CommentResponseItem>>
}