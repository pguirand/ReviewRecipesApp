package com.example.reviewrecipesapp.data.models

data class AllRecipesModel(
    val limit: Int,
    val recipes: List<RecipeModel>,
    val skip: Int,
    val total: Int
)