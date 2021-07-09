package com.itmedicious.spliff.model

import com.google.gson.annotations.SerializedName

data class LogIn(
    @SerializedName("email") val email: String? = null,
    @SerializedName("password") val password: String? = null
)
