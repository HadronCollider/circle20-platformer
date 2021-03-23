package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Boots

@Dao
interface BootsDao {
    @Query("SELECT * FROM boots WHERE inventoryId = :id")
    fun getBootsByChestId(id: Int): List<Boots>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBoots(boots: Boots)

    @Query("SELECT * FROM boots WHERE id=:id")
    fun getBootsById(id: Int): Boots
}