package com.example.demomovieapp.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

/**
 * Created by Imran Chowdhury on 2/9/2022.
 */

@Dao
public abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(vararg data: T)

    @Delete
    abstract fun delete(vararg data: T)

    @Delete
    abstract fun delete(data: List<T>)

}