package com.example.demomovieapp.data.network.services

import com.example.demomovieapp.data.model.MovieCastResponse
import com.example.demomovieapp.data.model.MovieDetailsResponse
import com.example.demomovieapp.data.model.TrendingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
interface MovieService {
    @GET("3/trending/movie/week")
    suspend fun getTrendingMovieList(
        @Query("api_key") key: String
    ): Response<TrendingMovieResponse>

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") key: String
    ): Response<MovieDetailsResponse>

    @GET("3/movie/{movieId}/credits")
    suspend fun getMovieCast(
        @Path("movieId") movieId: Int,
        @Query("api_key") key: String
    ): Response<MovieCastResponse>
}