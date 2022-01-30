package com.udacity.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.db.AsteroidDatabaseDao
import com.udacity.asteroidradar.models.Asteroid

class MainViewModel(
    val database: AsteroidDatabaseDao,
    application: Application
) : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    init {
        _asteroids.value = listOf(Asteroid(
            2,
            "Name",
            "4234",
            34.33,
            34.33,
            34.33,
            34.33,
            true
        ),Asteroid(
            2,
            "Name",
            "4234",
            34.33,
            34.33,
            34.33,
            34.33,
            true
        ),Asteroid(
            2,
            "Name",
            "4234",
            34.33,
            34.33,
            34.33,
            34.33,
            false
        ))
    }

}