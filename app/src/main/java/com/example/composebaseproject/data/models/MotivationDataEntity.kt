package com.example.composebaseproject.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "motiviations_table")
data class MotivationDataEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "category") var category: String? = "",
    @ColumnInfo(name = "text") var text: String? = "",
)