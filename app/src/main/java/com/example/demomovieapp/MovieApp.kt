package com.example.demomovieapp

import android.app.Application
import androidx.room.Room
import com.example.demomovieapp.data.db.AppDb
import com.example.demomovieapp.utils.Constants
import com.facebook.stetho.Stetho

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */
class MovieApp: Application() {

    companion object {
        lateinit var appDb: AppDb
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        appDb = Room.databaseBuilder(this, AppDb::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}