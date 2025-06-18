package com.example.reviewrecipesapp.intent

sealed class RecipesIntent {

    data object LoadAllRecipes : RecipesIntent()
    data class LoadRecipeById(val id:String) : RecipesIntent()
}