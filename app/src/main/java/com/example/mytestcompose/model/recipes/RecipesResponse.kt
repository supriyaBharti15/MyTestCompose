package com.example.mytestcompose.model.recipes

data class RecipesResponse(
    val limit: Int,
    val recipes: List<Recipe>,
    val skip: Int,
    val total: Int
)