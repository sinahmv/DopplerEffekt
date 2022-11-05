package de.sinah.dopplereffekt.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Doppler::class], version = 1, exportSchema = false)
abstract class DopplerDatabase : RoomDatabase() {
    abstract val dopplerDao : DopplerDao

    companion object{
        @Volatile
        private var INSTANCE : DopplerDatabase? = null

        fun getInstance(context: Context) : DopplerDatabase {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        DopplerDatabase::class.java,
                        "doppler_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}