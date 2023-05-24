package com.potemkin.musiciansocialmobileapp.api

import com.potemkin.musiciansocialmobileapp.models.*
import retrofit2.http.*

interface ApiService {


    @GET("users/all-users")
    suspend fun getUsers(): List<UserModel>

}
