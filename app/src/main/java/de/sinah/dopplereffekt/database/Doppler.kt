package de.sinah.dopplereffekt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doppler_table")
data class Doppler(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo(name = "frequency")
    val frequency : Double,

    @ColumnInfo(name = "speed")
    val speed : Double,

    @ColumnInfo(name = "result")
    val result : Double
)
