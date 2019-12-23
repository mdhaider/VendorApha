package com.instafinancials.vendoralpha.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class HistoryDataForDb(
    @PrimaryKey(autoGenerate = true)
    val historyId: Int,
    val gstTinNo: String,
    @ColumnInfo(name = "com_name")
    val comName: String,
    @ColumnInfo(name = "searched_day")
    val searchedDay: Date
   /* @ColumnInfo(name = "book_data")
    val bookWholeData: GstResponse*/
)