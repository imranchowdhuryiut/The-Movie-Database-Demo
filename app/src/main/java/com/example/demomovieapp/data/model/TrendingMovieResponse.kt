package com.example.demomovieapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Imran Chowdhury on 2/9/2022.
 */

data class TrendingMovieResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<Movie>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)