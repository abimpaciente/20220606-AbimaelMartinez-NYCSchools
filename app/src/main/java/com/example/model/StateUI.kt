package com.example.model

sealed class StateUI {
    object Loading : StateUI()
    class Success<T>(val response: T) : StateUI()
    class Error(val error: Exception) : StateUI()
    class Message(val text: String) : StateUI()
}