package com.example.reviewrecipesapp.data.service

import com.example.reviewrecipesapp.common.ApiConstants
import com.example.reviewrecipesapp.data.models.AllRecipesModel
import com.example.reviewrecipesapp.data.models.RecipeModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipesService {

    @GET(ApiConstants.ALL_RECIPES_ENDPOINT)
    suspend fun getAllRecipes() : Response<AllRecipesModel>

    @GET(ApiConstants.SINGLE_RECIPE_ENDPOINT)
    suspend fun getRecipeById(
        @Path("id") id : String
    ) : Response<RecipeModel>
}