package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Leggings

@Dao
interface LeggingsDao {
    @Query("SELECT * FROM leggings WHERE inventoryId = :id")
    fun getLeggingsByChestId(id: Int): List<Leggings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeggings(leggings: Leggings)

    @Query("SELECT * FROM leggings WHERE id=:id")
    fun getLeggingsById(id: Int): Leggings
}