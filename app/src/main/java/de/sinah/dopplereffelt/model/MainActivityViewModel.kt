package de.sinah.dopplereffelt.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sinah.dopplereffelt.database.Doppler
import de.sinah.dopplereffelt.database.DopplerRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: DopplerRepository
):ViewModel() {
    var frequency = 0.0
    var speed = 0.0
    var result = 0.0
    val doppler = repository.allDoppler

    fun insert() = viewModelScope.launch { repository.insert(Doppler(0,frequency, speed, result)) }

    fun deleteAll() = viewModelScope.launch { repository.deleteAll() }

    fun deleteDoppler(doppler:  Doppler) = viewModelScope.launch {
        repository.delete(doppler)
    }
}