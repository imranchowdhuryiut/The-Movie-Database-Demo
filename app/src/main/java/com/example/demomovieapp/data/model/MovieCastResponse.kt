package com.example.demomovieapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Imran Chowdhury on 2/10/2022.
 */


data class MovieCastResponse(
    @SerializedName("cast")
    var cast: List<Cast>? = null,
    @SerializedName("id")
    var id: Int?
)

data class Cast(
    @SerializedName("profile_path")
    var profilePath: String?
)