package com.example.demomovieapp.data.db.dao.movieDao

import androidx.room.Dao
import androidx.room.Query
import com.example.demomovieapp.data.db.dao.BaseDao
import com.example.demomovieapp.data.model.Movie

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */

@Dao
abstract class MovieDao: BaseDao<Movie>() {

    @Query("Delete FROM movies")
    abstract fun deleteAll()

    @Query("SELECT * from movies WHERE title LIKE '%'||:query||'%'")
    abstract fun searchMovies(query: String): List<Movie>

}