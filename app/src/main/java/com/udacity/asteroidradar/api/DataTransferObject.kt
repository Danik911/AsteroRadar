package com.udacity.asteroidradar.api

import android.provider.MediaStore
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.db.AsteroidDbEntity
import com.udacity.asteroidradar.models.Asteroid

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */

/**
 * Asteroid holds a list of Asteroids.
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "asteroids": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val videos: List<NetworkAsteroid>)


@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
    var id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

/**
 * Convert Network results to database objects
 */
fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
    return videos.map {
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

fun NetworkAsteroidContainer.asDatabaseModel(): Array<AsteroidDbEntity> {
    return videos.map {
        AsteroidDbEntity(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}