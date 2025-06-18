package com.example.reviewrecipesapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewrecipesapp.data.repository.RecipesRepository
import com.example.reviewrecipesapp.intent.RecipesIntent
import com.example.reviewrecipesapp.presentation.ui.states.RecipesUiState
import com.example.reviewrecipesapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: RecipesRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow(RecipesUiState())
    val uiState = _uiState.asStateFlow()
//    private val _singleRecipe = MutableStateFlow<UiState<RecipeModel>>(UiState.Loading)
//    val singleRecipe = _singleRecipe.asStateFlow()
//
//    val recipes = repository.getAllRecipes()
//        .stateIn(
//            started = SharingStarted.WhileSubscribed(5000),
//            scope = viewModelScope,
//            initialValue = UiState.Loading
//        )


    private fun getAllRecipes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(allRecipesState = UiState.Loading)
            repository.getAllRecipes().collectLatest {
                _uiState.value = _uiState.value.copy(allRecipesState = it)
            }
        }
    }

    private fun getRecipeById(id:String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(singleRecipeState = UiState.Loading)
            repository.getRecipeById(id).collectLatest {
                _uiState.value = _uiState.value.copy(singleRecipeState = it)
            }
        }
    }

    fun onIntent(intent: RecipesIntent) {
        when(intent) {
            RecipesIntent.LoadAllRecipes -> getAllRecipes()
            is RecipesIntent.LoadRecipeById -> getRecipeById(intent.id)
        }
    }
}