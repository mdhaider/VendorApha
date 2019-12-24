package com.instafinancials.vendoralpha.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.instafinancials.vendoralpha.models.GstResponse
import java.util.*

@Entity(indices = [Index(value = ["gstTinNo"], unique = true)])
data class HistoryDataForDb(
    @PrimaryKey(autoGenerate = true)
    val historyId: Int,
    val gstTinNo: String,
    @ColumnInfo(name = "com_name")
    val comName: String,
    @ColumnInfo(name = "searched_day")
    val searchedDay: Date,
    @ColumnInfo(name = "history_data")
    val fullHistoryData: GstResponse
)