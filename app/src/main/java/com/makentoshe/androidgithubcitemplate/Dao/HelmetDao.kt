package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Helmet

@Dao
interface HelmetDao {
    @Query("SELECT * FROM helmet WHERE inventoryId = :id")
    fun getHelmetByChestId(id: Int): List<Helmet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHelmet(helmet: Helmet)

    @Query("SELECT * FROM Helmet WHERE id=:id")
    fun getHelmetById(id: Int): Helmet
}