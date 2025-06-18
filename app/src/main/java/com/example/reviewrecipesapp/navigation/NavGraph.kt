package com.example.reviewrecipesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.reviewrecipesapp.intent.RecipesIntent
import com.example.reviewrecipesapp.presentation.ui.RecipeDetailScreen
import com.example.reviewrecipesapp.presentation.ui.RecipesScreen
import com.example.reviewrecipesapp.presentation.viewmodels.RecipesViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: RecipesViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.RecipesList.route
    ) {


        composable(Screen.RecipesList.route) {

            LaunchedEffect(Unit) {
                viewModel.onIntent(RecipesIntent.LoadAllRecipes)
            }
            RecipesScreen(
                modifier = modifier,
                uiState = state.allRecipesState,
                onRecipeClick = { id ->
                    navController.navigate(Screen.RecipeDetail.createRoute(id))}
            )
        }

        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""

            LaunchedEffect(id) {
                viewModel.onIntent(RecipesIntent.LoadRecipeById(id))
            }

            RecipeDetailScreen(
                modifier = modifier,
                uiState = state.singleRecipeState,
                onBack = { navController.popBackStack() }
            )
        }
    }
}