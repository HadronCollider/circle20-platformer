package com.makentoshe.androidgithubcitemplate

import androidx.room.Entity
import androidx.room.PrimaryKey

interface Inventory {

    val id: Int

    val capacity: Int

    //fun fetch(item: Item): Item?

    fun carry(item: Item)

    fun carry(sword: Sword)

    fun carry(bow: Bow)

    fun carry(staff: Staff)
}

@Entity
class HeroInventory(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val capacity: Int = 15): Inventory {

    override fun carry(item: Item) {
        item.inventoryId = id
    }

    override fun carry(sword: Sword) {
        sword.inventoryId = id
        swordDao?.insertSword(sword)
    }

    override fun carry(bow: Bow) {
        bow.inventoryId = id
        bowDao?.insertBow(bow)
    }

    override fun carry(staff: Staff) {
        staff.inventoryId = id
        staffDao?.insertStaff(staff)
    }
}

@Entity
class ChestInventory(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val capacity: Int = 10): Inventory {

    override fun carry(item: Item) {
        item.inventoryId = id
    }

    override fun carry(sword: Sword) {
        sword.inventoryId = id
        swordDao?.insertSword(sword)
    }

    override fun carry(bow: Bow) {
        bow.inventoryId = id
        bowDao?.insertBow(bow)
    }

    override fun carry(staff: Staff) {
        staff.inventoryId = id
        staffDao?.insertStaff(staff)
    }
}