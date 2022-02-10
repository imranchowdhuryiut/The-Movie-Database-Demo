package com.example.demomovieapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demomovieapp.data.db.dao.movieDao.MovieDao
import com.example.demomovieapp.data.model.Movie

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
@Database(
    entities = [
        Movie::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDb : RoomDatabase () {
    abstract fun movieDao(): MovieDao
}