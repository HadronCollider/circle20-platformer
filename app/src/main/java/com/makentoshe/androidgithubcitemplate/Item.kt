package com.makentoshe.androidgithubcitemplate

open class Item(
    val name: String,
    val weight:Int,
    val strRequired: Int,
    val dexRequired: Int) {}

open class Weapon(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    val damage: Int
):Item(name, weight, strRequired, dexRequired)

class Sword(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int
):Weapon(name, weight, strRequired, dexRequired, damage)

class Bow(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int
):Weapon(name, weight, strRequired, dexRequired, damage)

class Staff(
    name: String,
    weight: Int,
    strRequired: Int,
    dexRequired: Int,
    damage: Int
):Weapon(name, weight, strRequired, dexRequired, damage)