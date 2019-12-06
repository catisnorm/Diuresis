package com.example.diuresis

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class VolumeRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: VolumeRecordRepository
    val allRecords: LiveData<List<VolumeRecord>>

    init {
        val volumeRecordDao = VolumeRecordsDatabase.getDatabase(application).volumeRecordDao()
        repository = VolumeRecordRepository(volumeRecordDao)
        allRecords = repository.records
    }

    fun insert(volumeRecord: VolumeRecord) = viewModelScope.launch {
        repository.insert(volumeRecord)
    }
}
