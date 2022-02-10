package com.example.demomovieapp.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


/**
 * Created by Imran Chowdhury on 2/8/2022.
 */
@Entity(tableName = "movies")
data class Movie(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @PrimaryKey
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
    @SerializedName("title")
    var title: String?,
    @SerializedName("vote_average")
    var voteAverage: Double?
)