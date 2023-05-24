package com.potemkin.musiciansocialmobileapp.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.potemkin.musiciansocialmobileapp.api.ApiHelper
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import retrofit2.HttpException


class UserViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val _userState = MutableStateFlow<List<UserModel>>(emptyList())
    val userState: StateFlow<List<UserModel>> = _userState

    fun getUsers():List<UserModel>{
        var userResponse: List<UserModel>? = null
        viewModelScope.launch {
            try {
                userResponse = apiHelper.getUsers()
                _userState.tryEmit(userResponse!!)
                println("huihuihuijopapopa"+ userState)
            }catch (e: Exception) {
                println("huihuihui"+e.message)
            }
        }
        return userResponse!!
    }
    /*fun authenticateUser(request: AuthRequest) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val authResponse = apiHelper.auth(request)
                _authState.value = AuthState.Success(authResponse)
                // TODO:
                // запись в шейред преференсес
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Возникла ошибка")
            }
        }
    }*/
}