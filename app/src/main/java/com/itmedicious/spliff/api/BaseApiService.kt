package com.itmedicious.spliff.api

import com.google.gson.JsonObject
import com.itmedicious.spliff.model.LogIn
import com.itmedicious.spliff.model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {


    @Headers("Content-Type: application/json")
    @POST("/api/v1/login")
    fun logIn(
        @Body login: LogIn?,
    ): Call<LoginResponse>


}
