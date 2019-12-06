package com.example.diuresis

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity
data class VolumeRecord (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val volume: Double,
    val createDate: Date
)
