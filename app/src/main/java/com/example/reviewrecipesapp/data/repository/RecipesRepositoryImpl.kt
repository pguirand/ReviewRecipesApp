package com.example.reviewrecipesapp.data.repository

import com.example.reviewrecipesapp.data.models.AllRecipesModel
import com.example.reviewrecipesapp.data.models.RecipeModel
import com.example.reviewrecipesapp.data.service.RecipesService
import com.example.reviewrecipesapp.utils.UiState
import com.example.reviewrecipesapp.utils.makeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val service: RecipesService
) : RecipesRepository{
    override fun getAllRecipes(): Flow<UiState<AllRecipesModel>> =
        makeApiCall { service.getAllRecipes() }


    override fun getRecipeById(id: String): Flow<UiState<RecipeModel>> {
        return makeApiCall { service.getRecipeById(id) }
    }
}