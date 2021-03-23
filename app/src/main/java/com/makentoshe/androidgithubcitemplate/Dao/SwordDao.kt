package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.*
import com.makentoshe.androidgithubcitemplate.Sword

@Dao
interface SwordDao {

    @Query("SELECT * FROM sword WHERE inventoryId = :id")
    fun getSwordByChestId(id: Int): List<Sword>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSword(sword: Sword)

    @Query("SELECT * FROM sword WHERE id=:id")
    fun getSwordById(id: Int): Sword
}