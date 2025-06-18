package com.example.reviewrecipesapp.navigation

sealed class Screen(val route : String) {
    data object RecipesList : Screen("recipes_list")
    data object RecipeDetail : Screen("recipe_detail/{id}") {
        fun createRoute(id:String) = "recipe_detail/$id"
    }
}