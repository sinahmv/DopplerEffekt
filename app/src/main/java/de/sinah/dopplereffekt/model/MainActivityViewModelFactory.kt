package de.sinah.dopplereffekt.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.sinah.dopplereffekt.database.DopplerRepository

class MainActivityViewModelFactory(
    private val dopplerRepository: DopplerRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(dopplerRepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model class")
    }
}