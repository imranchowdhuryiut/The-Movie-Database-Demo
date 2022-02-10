package com.example.demomovieapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Imran Chowdhury on 2/9/2022.
 */


data class MovieDetailsResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("vote_average")
    var voteAverage: Double?
)