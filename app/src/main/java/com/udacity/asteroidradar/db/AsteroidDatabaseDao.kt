package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AsteroidDatabaseDao {

    @Query("select * from ASTEROID_TABLE")
    fun getAllAsteroids(): LiveData<List<AsteroidDbEntity>>


}