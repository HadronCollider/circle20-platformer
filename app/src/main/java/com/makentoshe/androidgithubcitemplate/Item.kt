package com.makentoshe.androidgithubcitemplate

import android.graphics.drawable.Drawable

open class Item(
    val name: String,
    val weight:Int,
    val strRequired: Int,
    val dexRequired: Int,
    val draw : Drawable?) {}

open class Weapon(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    val damage: Int,
    draw : Drawable?
):Item(name, weight, strRequired, dexRequired, draw)

class Sword(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Drawable?
):Weapon(name, weight, strRequired, dexRequired, damage, draw)

class Bow(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Drawable?
):Weapon(name, weight, strRequired, dexRequired, damage, draw)

class Staff(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int,
    draw : Drawable?
):Weapon(name, weight, strRequired, dexRequired, damage, draw)

open class Armor(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    val def: Int,
    draw : Drawable?
):Item(name, weight, strRequired, dexRequired, draw)

class Chestplate(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Drawable?
):Armor(name, weight, strRequired, dexRequired, def, draw)

class Leggings(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Drawable?
):Armor(name, weight, strRequired, dexRequired, def, draw)

class Helmet(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Drawable?
):Armor(name, weight, strRequired, dexRequired, def, draw)

class Boots(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    def: Int,
    draw : Drawable?
):Armor(name, weight, strRequired, dexRequired, def, draw)