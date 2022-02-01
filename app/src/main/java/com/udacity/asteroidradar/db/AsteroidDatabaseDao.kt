package com.udacity.asteroidradar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.util.Constants.TABLE_NAME


@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY closeApproachDate")
    fun getAllAsteroids(): LiveData<List<AsteroidDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(asteroids: List<AsteroidDbEntity>)


}