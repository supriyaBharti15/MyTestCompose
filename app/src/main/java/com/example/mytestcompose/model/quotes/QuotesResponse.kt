package com.example.mytestcompose.model.quotes

data class QuotesResponse(
    val limit: Int,
    val quotes: List<Quote>,
    val skip: Int,
    val total: Int
)