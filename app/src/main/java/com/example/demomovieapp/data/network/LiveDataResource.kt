package com.example.demomovieapp.data.network

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */

class LiveDataResource<T> private constructor(val status: Status, val data: T?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T? = null): LiveDataResource<T> {
            return LiveDataResource(
                Status.SUCCESS,
                data
            )
        }

        fun <T> error(errorData: T? = null): LiveDataResource<T> {
            return LiveDataResource(
                Status.ERROR,
                errorData
            )
        }

        fun <T> loading(data: T? = null): LiveDataResource<T> {
            return LiveDataResource(
                Status.LOADING,
                data
            )
        }
    }
}