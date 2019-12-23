package com.instafinancials.vendoralpha.db

import androidx.room.*

@Dao
interface BookmarkDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmarkDataForDb: BookmarkDataForDb)

    @Update
    fun updateDayRoutine(bookmarkDataForDb: BookmarkDataForDb)

    @Delete
    fun deleteDayRoutine(bookmarkDataForDb: BookmarkDataForDb)

    @Query("SELECT * FROM BookmarkDataForDb")
    fun getBookmark(): List<BookmarkDataForDb>
}