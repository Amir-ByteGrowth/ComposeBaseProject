package com.example.composebaseproject.data.models

data class UserListDumb(
    val `data`: List<Data>,
    val message: String,
    val token: String
)

data class Data(
    val id: String,
    val name: String
)