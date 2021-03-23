package com.makentoshe.androidgithubcitemplate.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.makentoshe.androidgithubcitemplate.Staff

@Dao
interface StaffDao {

    @Query("SELECT * FROM staff WHERE inventoryId = :id")
    fun getStaffByChestId(id: Int): List<Staff>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStaff(staff: Staff)

    @Query("SELECT * FROM staff WHERE id = :id")
    fun getStaffById(id: Int): Staff
}