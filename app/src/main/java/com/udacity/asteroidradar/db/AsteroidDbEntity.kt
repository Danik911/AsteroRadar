package com.udacity.asteroidradar.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.util.Constants.ASTEROID_TABLE


@Entity(tableName = ASTEROID_TABLE)
data class AsteroidDbEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)