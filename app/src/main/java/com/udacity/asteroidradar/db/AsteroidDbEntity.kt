package com.udacity.asteroidradar.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.util.NetworkConstants.TABLE_NAME
import java.io.Serializable


@Entity(tableName = TABLE_NAME)
data class AsteroidDbEntity(
    @PrimaryKey
    var id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
): Serializable

fun List<AsteroidDbEntity>.asDomainModel(): List<Asteroid>{
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}