package com.istudio.pokedex.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    // The data is mandatory because in a successful response, The API has data
    class Success<T>(data: T) : Resource<T>(data)
    // The data is not mandatory because the in error state the data will be null, But message is mandatory because error message has to be sent
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    // There is just emission for this indicating loading is displayed or not displayed
    class Loading<T> : Resource<T>()
}
