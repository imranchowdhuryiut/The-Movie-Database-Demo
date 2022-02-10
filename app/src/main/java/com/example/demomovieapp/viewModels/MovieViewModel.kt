package com.example.demomovieapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demomovieapp.data.model.Movie
import com.example.demomovieapp.data.model.MovieCastResponse
import com.example.demomovieapp.data.model.MovieDetailsResponse
import com.example.demomovieapp.data.network.ApiResponse
import com.example.demomovieapp.data.network.LiveDataResource
import com.example.demomovieapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
class MovieViewModel: ViewModel() {

    private val mRepo: MovieRepository by lazy { MovieRepository() }

    fun getMovieDetails(movieId: Int): LiveData<LiveDataResource<ApiResponse<MovieDetailsResponse>>> {
        return requestApiData {
            mRepo.getMovieDetails(movieId)
        }
    }

    fun getMovieCast(movieId: Int): LiveData<LiveDataResource<ApiResponse<MovieCastResponse>>> {
        return requestApiData {
            mRepo.getMovieCast(movieId)
        }
    }

    fun getMovieList(): LiveData<LiveDataResource<ApiResponse<List<Movie>>>> {
        return requestApiData {
            mRepo.getTrendingMovieList()
        }
    }

    private fun <T> requestApiData(
        processData: ((T?) -> Unit)? = null,
        requestApiResponse: suspend () -> ApiResponse<T>?
    ): LiveData<LiveDataResource<ApiResponse<T>>> {
        return liveData(Dispatchers.Default) {
            emit(LiveDataResource.loading())
            val data = requestApiResponse()
            if (data?.status == true) {
                processData?.invoke(data.data)
                emit(LiveDataResource.success(data))
            } else emit(LiveDataResource.error(data))
        }
    }

    fun searchMovie(query: String):LiveData<List<Movie>> {
        return liveData(Dispatchers.Default) {
            emit(mRepo.searchMovie(query))
        }
    }

}