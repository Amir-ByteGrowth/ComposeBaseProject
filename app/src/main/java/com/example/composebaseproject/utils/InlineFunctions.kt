package com.example.composebaseproject.utils

import com.example.composebaseproject.data.remote.ResponseError
import com.google.gson.Gson

val gsonObj = Gson()

fun extractErrorMessage(errorMessagesJson: String): ResponseError {

    gsonObj.serializeNulls()
    val errorObj = gsonObj.fromJson(errorMessagesJson, ResponseError::class.java)
    val msg = errorObj.message

    return errorObj
}