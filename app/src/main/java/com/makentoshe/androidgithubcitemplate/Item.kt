package com.makentoshe.androidgithubcitemplate

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey

open class Item(
    @PrimaryKey
    val id: Int,
    var inventoryId : Int,
    val name: String,
    val weight:Int,
    val strRequired: Int,
    val dexRequired: Int,
    val draw : Int) {}

open class Weapon(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    val damage: Int,
    draw : Int
):Item(id, inventoryId, name, weight, strRequired, dexRequired, draw)

@Entity
class Sword(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Int
):Weapon(id, inventoryId, name, weight, strRequired, dexRequired, damage, draw)

@Entity
class Bow(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Int
):Weapon(id, inventoryId, name, weight, strRequired, dexRequired, damage, draw)

@Entity
class Staff(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Int
):Weapon(id, inventoryId, name, weight, strRequired, dexRequired, damage, draw)

open class Armor(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    val def: Int,
    draw : Int
):Item(id, inventoryId, name, weight, strRequired, dexRequired, draw)

@Entity
class Chestplate(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Int
):Armor(id, inventoryId, name, weight, strRequired, dexRequired, def, draw)

@Entity
class Leggings(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Int
):Armor(id, inventoryId, name, weight, strRequired, dexRequired, def, draw)

@Entity
class Helmet(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Int
):Armor(id, inventoryId, name, weight, strRequired, dexRequired, def, draw)

@Entity
class Boots(
    id: Int,
    inventoryId : Int,
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Int
):Armor(id, inventoryId, name, weight, strRequired, dexRequired, def, draw)