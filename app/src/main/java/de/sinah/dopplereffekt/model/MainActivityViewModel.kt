package de.sinah.dopplereffekt.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.sinah.dopplereffekt.database.Doppler
import de.sinah.dopplereffekt.database.DopplerRepository
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