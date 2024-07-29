package com.example.composebaseproject.data.remote

import com.example.composebaseproject.data.models.MotivationDataItem
import com.example.composebaseproject.data.models.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {


    @GET("/v3/b/666ad9ace41b4d34e402b784?meta=false")
    suspend fun getMotivationList(
        @Header("X-MASTER-KEY") header: String ="\$2a\$10\$1h7.xxnkJQ59UDIiq2QlrOcOCw4ocKVl6Qfo3Q9W13PnY8DoFxWeq",
    ): Response<List<MotivationDataItem>>


    @GET("/v3/b/66a36f21ad19ca34f88d0d29?meta=false")
    suspend fun getUserList(
        @Header("X-MASTER-KEY") header: String = "\$2a\$10\$1h7.xxnkJQ59UDIiq2QlrOcOCw4ocKVl6Qfo3Q9W13PnY8DoFxWeq",
    ): Response<ResponseTemplate<List<UserData>>>



}