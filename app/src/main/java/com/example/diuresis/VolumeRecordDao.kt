package com.example.diuresis

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VolumeRecordDao {
    @Query("SELECT * from VolumeRecord")
    fun getAllRecords(): LiveData<List<VolumeRecord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(volumeRecord: VolumeRecord)
}
