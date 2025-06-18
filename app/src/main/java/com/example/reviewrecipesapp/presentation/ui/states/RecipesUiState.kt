package com.example.reviewrecipesapp.presentation.ui.states

import com.example.reviewrecipesapp.data.models.AllRecipesModel
import com.example.reviewrecipesapp.data.models.RecipeModel
import com.example.reviewrecipesapp.utils.UiState

data class RecipesUiState(
    val allRecipesState : UiState<AllRecipesModel> = UiState.Loading,
    val singleRecipeState : UiState<RecipeModel> = UiState.Loading
)

