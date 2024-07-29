package com.example.composebaseproject.data.remote


sealed class Resource<out T> {
    class Initial<T> : Resource<T>()
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val status: Status, val data: T?, val message: String?, val responseError: ResponseError?) : Resource<T>()

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }


    companion object {

        fun <T> initial() = Initial<T> ()

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(message: String, data: T? = null, responseError: ResponseError?) =
            Error<T>(Status.ERROR, data, message,responseError)

    }



}