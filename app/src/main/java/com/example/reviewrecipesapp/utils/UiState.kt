package com.example.reviewrecipesapp.utils

sealed class UiState<out T : Any> {

    data class Success<out T : Any>(val data : T) : UiState<T>()
    data class Error(val error : Exception) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
}