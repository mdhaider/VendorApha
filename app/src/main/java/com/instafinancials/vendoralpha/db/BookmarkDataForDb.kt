package com.instafinancials.vendoralpha.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class BookmarkDataForDb(
    @PrimaryKey(autoGenerate = true)
    val bookmarkId: Int,
    val gstTinNo: String,
    @ColumnInfo(name = "com_name")
    val comName: String,
    @ColumnInfo(name = "bookmark_day")
    val dueDay: Date
   /* @ColumnInfo(name = "book_data")
    val bookWholeData: GstResponse*/
)