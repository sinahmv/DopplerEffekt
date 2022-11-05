package de.sinah.dopplereffekt.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DopplerDao {
    @Insert
    suspend fun insert (doppler: Doppler)

    @Query("SELECT * FROM doppler_table ORDER BY id ASC")
    fun getAllDopplerSortedByID(): LiveData<List<Doppler>>

    @Query("DELETE FROM doppler_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(doppler: Doppler)
}