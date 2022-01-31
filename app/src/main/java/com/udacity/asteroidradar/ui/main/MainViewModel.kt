package com.udacity.asteroidradar.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.udacity.asteroidradar.db.AsteroidDatabase.Companion.getDatabase
import com.udacity.asteroidradar.models.Asteroid
import com.udacity.asteroidradar.models.PictureOfDay
import com.udacity.asteroidradar.repositoy.AsteroidRepository
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val asteroidRepository = AsteroidRepository(database)
    val asteroids = asteroidRepository.asteroids

    private val _pictureOfDay = MutableLiveData<PictureOfDay>()
    val pictureOfDay: LiveData<PictureOfDay>
        get() = _pictureOfDay

    private val _navigateToDetailFragment = MutableLiveData<Asteroid?>()
    val navigateToDetailFragment
        get() = _navigateToDetailFragment


    private val mockData = false
    private val _mockAsteroids = MutableLiveData<List<Asteroid>>()
    val mockAsteroids : LiveData<List<Asteroid>>
        get() = _mockAsteroids

    init {
        if(mockData) {
            mockData()
        } else {
            refreshAsteroids()
            getPictureOfDay()
        }
    }

    private fun mockData() {

        val dataList = mutableListOf<Asteroid>()

        var count = 1
        while (count <= 10) {

            val data = Asteroid(
                count.toLong(),
                "codename:$count",
                "XXXX-XX-XX",
                77.0,
                88.0,
                99.8,
                66.6,
                true)

            dataList.add(data)

            ++count
        }

        _mockAsteroids.postValue(dataList)
    }

    fun onAsteroidItemClick(data: Asteroid) {
        _navigateToDetailFragment.value = data
    }

    fun onDetailFragmentNavigated() {
        _navigateToDetailFragment.value = null
    }

    private fun refreshAsteroids() {
        viewModelScope.launch {
            try {
                asteroidRepository.refreshAsteroids()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getPictureOfDay() {
        viewModelScope.launch {
            try {
                _pictureOfDay.value = asteroidRepository.getPictureOfDay()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}