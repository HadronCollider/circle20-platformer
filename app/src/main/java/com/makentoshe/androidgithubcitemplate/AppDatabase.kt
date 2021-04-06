package com.makentoshe.androidgithubcitemplate

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.makentoshe.androidgithubcitemplate.Dao.*

@Database(entities = [
    Character::class, //+
    //Hero::class, //-
    //HeroInventory::class, //-
    //ChestInventory::class, //-
    Sword::class, //+
    Bow::class, //+
    Staff::class, //+
    Chestplate::class, //+
    Leggings::class, //+
    Helmet::class, //+
    Boots::class], //+
    version = 3)
public abstract class AppDatabase: RoomDatabase() {
    abstract fun swordDao(): SwordDao
    abstract fun bowDao(): BowDao
    abstract fun staffDao(): StaffDao
    abstract fun characterDao(): CharacterDao
    abstract fun chestplateDao(): ChestplateDao
    abstract fun bootsDao(): BootsDao
    abstract fun leggingsDao(): LeggingsDao
    abstract  fun helmetDao(): HelmetDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null

        }
    }
}