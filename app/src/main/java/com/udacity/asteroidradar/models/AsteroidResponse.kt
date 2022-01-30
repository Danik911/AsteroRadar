package com.udacity.asteroidradar.models

data class AsteroidResponse(
    val asteroids: List<Asteroid>,
    val status: String,
    val totalResults: Int
)