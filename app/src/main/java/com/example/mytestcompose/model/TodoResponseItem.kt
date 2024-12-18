package com.example.mytestcompose.model

data class TodoResponseItem(
    val completed: Boolean,
    val id: String,
    val title: String,
    val userId: String
)