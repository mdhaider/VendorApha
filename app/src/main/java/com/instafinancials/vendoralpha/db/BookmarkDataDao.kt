package com.instafinancials.vendoralpha.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookmarkDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmarkDataForDb: BookmarkDataForDb)

    @Update
    fun updateBookmark(bookmarkDataForDb: BookmarkDataForDb)

    @Delete
    fun deleteBookmark(bookmarkDataForDb: BookmarkDataForDb)

    @Query("SELECT * FROM BookmarkDataForDb")
    fun getBookmark(): LiveData<List<BookmarkDataForDb>>
}