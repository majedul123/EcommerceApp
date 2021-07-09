package com.itmedicious.spliff.model

import com.google.gson.annotations.SerializedName


data class ExpireAt (

    @SerializedName("date") val date : String,
    @SerializedName("timezone_type") val timezone_type : Int,
    @SerializedName("timezone") val timezone : String
)