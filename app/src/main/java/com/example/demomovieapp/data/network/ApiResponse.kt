package com.example.demomovieapp.data.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */

data class ApiResponse<T>(
    @SerializedName("status")
    val status: Boolean,

    @SerializedName("message")
    val message: String?,

    @SerializedName("data")
    val data: T? = null,
)