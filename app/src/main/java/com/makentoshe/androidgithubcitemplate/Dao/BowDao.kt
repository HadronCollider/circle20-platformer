package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Bow

@Dao
interface BowDao {

    @Query("SELECT * FROM bow WHERE inventoryId = :id")
    fun getBowByChestId(id: Int): List<Bow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBow(bow: Bow)

    @Query("SELECT * FROM bow WHERE id = :id")
    fun getBowById(id: Int): Bow
}