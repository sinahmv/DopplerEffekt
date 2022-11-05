package de.sinah.dopplereffekt.database

import androidx.lifecycle.LiveData

class DopplerRepository (
    private val DopplerDao: DopplerDao
){

    val allDoppler: LiveData<List<Doppler>> = DopplerDao.getAllDopplerSortedByID()

    suspend fun insert(Doppler: Doppler){
        DopplerDao.insert(Doppler)
    }
    suspend fun deleteAll(){
        DopplerDao.deleteAll()
    }
    suspend fun delete(Doppler: Doppler){
        DopplerDao.delete(Doppler)
    }
}