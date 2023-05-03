package ru.pakarp.GiefptAbitur.domain.model

sealed class NetworkResult <T>(
        val data: T? = null,
        val message: String? = null
        ) {
    class Success<T>(data: T?): NetworkResult<T>(data = data)

    class Error<T>(message: String?): NetworkResult<T>(message = message)

    class Loading<T>(message: String?): NetworkResult<T>(message = message)

}