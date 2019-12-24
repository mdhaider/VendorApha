package com.instafinancials.vendoralpha.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoryDataDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(historyDataForDb: HistoryDataForDb)

    @Update
    fun updateHistory(historyDataForDb: HistoryDataForDb)

    @Delete
    fun deleteHistory(historyDataForDb: HistoryDataForDb)

    @Query("SELECT * FROM HistoryDataForDb")
    fun getHistory(): LiveData<List<HistoryDataForDb>>
}