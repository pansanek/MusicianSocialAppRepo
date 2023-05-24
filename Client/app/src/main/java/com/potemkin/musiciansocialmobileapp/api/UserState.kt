package com.potemkin.musiciansocialmobileapp.api

sealed class UserState {
    object Loading : UserState()
    data class Success(val userResponse: UserResponse) : UserState()
    data class Error(val message: String) : UserState()
}
