package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Chestplate

@Dao
interface ChestplateDao {
    @Query("SELECT * FROM chestplate WHERE inventoryId = :id")
    fun getChestplateByChestId(id: Int): List<Chestplate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChestplate(chestplate: Chestplate)

    @Query("SELECT * FROM chestplate WHERE id=:id")
    fun getChestplateById(id: Int): Chestplate
}