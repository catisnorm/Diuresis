package com.example.diuresis

import androidx.lifecycle.LiveData

class VolumeRecordRepository(private val volumeRecordDao: VolumeRecordDao) {
    val records: LiveData<List<VolumeRecord>> = volumeRecordDao.getAllRecords()

    suspend fun insert(volumeRecord: VolumeRecord) {
        volumeRecordDao.insert(volumeRecord)
    }
}
