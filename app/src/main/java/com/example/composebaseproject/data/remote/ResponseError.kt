package com.example.composebaseproject.data.remote

data class ResponseError(
    val message: String?,
    val code: Int?,
    val accessToken: String?
)
