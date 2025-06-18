package com.example.reviewrecipesapp.data.repository

import com.example.reviewrecipesapp.data.models.AllRecipesModel
import com.example.reviewrecipesapp.data.models.RecipeModel
import com.example.reviewrecipesapp.utils.UiState
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    fun getAllRecipes() : Flow<UiState<AllRecipesModel>>

    fun getRecipeById(id : String) : Flow<UiState<RecipeModel>>

}