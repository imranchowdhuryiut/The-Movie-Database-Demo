package com.example.demomovieapp.data.network

import com.example.demomovieapp.utils.Constants
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * Created by Imran Chowdhury on 2/8/2022.
 */
object ApiClient {

    val mInstance: Retrofit by lazy { createInstance() }

    private val gson by lazy { GsonBuilder().create() }

    private fun createInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()
    }

    inline fun <reified T> createApiService(): T {
        return mInstance.create(T::class.java)
    }

}