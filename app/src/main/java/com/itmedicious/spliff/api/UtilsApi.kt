package com.itmedicious.spliff.api


object UtilsApi {
    val BASE_URL: String = "http://sandbox.emdexapi.com"
    val apiService: BaseApiService
        get() = RetrofitClient.getClient(BASE_URL)!!.create(BaseApiService::class.java)
}
