package com.example.composebaseproject.data.models



data class SampleUserList(
    val `data`: List<UserData>,
    val message: String,
    val token: String
)

data class UserData(
    val id: String,
    val name: String
)