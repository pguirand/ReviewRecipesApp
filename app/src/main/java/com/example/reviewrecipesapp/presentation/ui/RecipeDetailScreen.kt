package com.example.reviewrecipesapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.reviewrecipesapp.R
import com.example.reviewrecipesapp.data.models.RecipeModel
import com.example.reviewrecipesapp.utils.UiState

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    uiState : UiState<RecipeModel>,
    onBack : () -> Unit
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
            val recipe = uiState.data

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

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
                Spacer(modifier = Modifier.weight(1f))
                FloatingActionButton(
                    onClick =  onBack ,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    }


}