package com.itmedicious.spliff.model

import com.google.gson.annotations.SerializedName

data class Success (

    @SerializedName("token") val token : String,
    @SerializedName("expires_at") val expires_at : ExpireAt
)