package com.example.reviewrecipesapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.reviewrecipesapp.R
import com.example.reviewrecipesapp.data.models.AllRecipesModel
import com.example.reviewrecipesapp.data.models.RecipeModel
import com.example.reviewrecipesapp.utils.UiState

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    uiState : UiState<AllRecipesModel>,
    onRecipeClick : (String) -> Unit
) {

    when(uiState) {
        is UiState.Error -> {
            Text(uiState.error.localizedMessage?:"Unknown error")
        }
        UiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center 
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            val recipes = uiState.data.recipes
            
            LazyColumn(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) { 
                items(recipes) { recipe ->
                    RecipeItem(recipe) {
                       onRecipeClick(recipe.id.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: RecipeModel, onItemClick : () -> Unit) {
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = onItemClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Rating : ${(3..5).random()}/5 ⭐")
            Text(recipe.name)
            Text(recipe.cuisine)
            Text(recipe.difficulty)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.image)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
            Text("***")
        }
    }
}
