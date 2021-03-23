package com.makentoshe.androidgithubcitemplate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val maxHP: Int,
    val def: Int,
    val damage: Int,
    val heroDrawable: Int,
    val weaponDrawable: Int) {

    var HP = maxHP

    fun getAttack(damage:Int) {
        HP -= damage-def
    }

    fun maxHeal(){
        HP = maxHP
    }

}

class Hero(
    id: Int,
    maxHP: Int,
    val maxMP: Int,
    def: Int,
    damage: Int,
    heroDrawable: Int,
    weaponDrawable: Int):Character(id,maxHP, def, damage, heroDrawable, weaponDrawable){

    var MP = maxMP
    val inventory = HeroInventory(0)

    fun heal(){
        HP+=maxHP/5
        MP-=25
        if(HP > maxHP)
            HP = maxHP
    }

    fun regenMP() {
        MP += 5
        if(MP > maxMP)
            MP = maxMP
    }
}