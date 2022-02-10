package com.example.demomovieapp.data.repository

import com.example.demomovieapp.MovieApp
import com.example.demomovieapp.data.model.Movie
import com.example.demomovieapp.data.model.MovieCastResponse
import com.example.demomovieapp.data.model.MovieDetailsResponse
import com.example.demomovieapp.data.network.ApiClient.createApiService
import com.example.demomovieapp.data.network.ApiResponse
import com.example.demomovieapp.data.network.services.MovieService
import com.example.demomovieapp.utils.Constants

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
class MovieRepository {

    private val mWebService = createApiService<MovieService>()

    suspend fun getMovieCast(movieId: Int): ApiResponse<MovieCastResponse> {
        val response = mWebService.getMovieCast(
            movieId,
            Constants.api_key
        )
        return if (response.isSuccessful) {
            ApiResponse(true, "", response.body())
        } else {
            ApiResponse(false, "Something went wrong", null)
        }
    }

    suspend fun getMovieDetails(movieId: Int): ApiResponse<MovieDetailsResponse> {
        val response = mWebService.getMovieDetails(
            movieId,
            Constants.api_key
        )
        return if (response.isSuccessful) {
            ApiResponse(true, "", response.body())
        } else {
            ApiResponse(false, "Something went wrong", null)
        }
    }

    suspend fun getTrendingMovieList(): ApiResponse<List<Movie>> {
        val response = mWebService.getTrendingMovieList(Constants.api_key)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody?.results != null) {
                responseBody.results?.let { list ->
                    MovieApp.appDb.movieDao().save(list)
                }
            }
            ApiResponse(true, "", responseBody?.results)
        } else {
            ApiResponse(false, "Something went wrong", null)
        }
    }

    fun searchMovie(query: String): List<Movie> {
        return MovieApp.appDb.movieDao().searchMovies(query)
    }


}