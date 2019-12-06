package com.example.diuresis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [VolumeRecord::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VolumeRecordsDatabase : RoomDatabase() {
    abstract fun volumeRecordDao(): VolumeRecordDao
    companion object {
        @Volatile
        private var INSTANCE: VolumeRecordsDatabase? = null

        fun getDatabase(context: Context): VolumeRecordsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VolumeRecordsDatabase::class.java,
                    "volume_records_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
