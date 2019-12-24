package com.instafinancials.vendoralpha.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.instafinancials.vendoralpha.models.GstResponse
import java.util.*

@Entity(indices = [Index(value = ["gstTinNo"], unique = true)])
data class BookmarkDataForDb(
    @PrimaryKey(autoGenerate = true)
    val bookmarkId: Int,
    val gstTinNo: String,
    @ColumnInfo(name = "com_name")
    val comName: String,
    @ColumnInfo(name = "bookmark_day")
    val bookDay: Date,
    @ColumnInfo(name = "book_data")
    val fullBookmarkData: GstResponse
)