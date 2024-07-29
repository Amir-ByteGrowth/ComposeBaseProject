package com.example.composebaseproject.data.remote

class ResponseTemplate<T>(
    val accessToken: String?,
    val code: Int?,
    val `data`: T?,
    val message: String?,
)

