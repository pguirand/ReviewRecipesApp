package com.example.reviewrecipesapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException


inline fun <T : Any> makeApiCall(
    crossinline apiCall : suspend () -> Response<T>
) : Flow<UiState<T>> = flow {
    emit(UiState.Loading)
    val response = apiCall()
    if (response.isSuccessful) {
        response.body()?.let {
            emit(UiState.Success(it))
        } ?: emit(UiState.Error(Exception("no data found")))
    } else {
        emit(UiState.Error(Exception("${response.code()} : ${response.message()}")))
    }
} .catch { e ->
    val customError = when(e) {
        is SocketTimeoutException -> UiState.Error(Exception("request timeout "))
        is UnknownHostException -> UiState.Error(Exception("request timeout "))
        is IOException -> UiState.Error(Exception("request timeout "))
        else -> UiState.Error(Exception(e))
    }
    emit(customError)
}