package com.itmedicious.spliff.model

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("success") val success : Success
)