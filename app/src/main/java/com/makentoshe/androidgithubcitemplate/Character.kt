package com.makentoshe.androidgithubcitemplate

import android.graphics.drawable.Drawable

open class Character(internal val maxHP: Int,private val def: Int, val damage: Int,val heroDrawable: Drawable?,val weaponDrawable: Drawable?) {

    internal var HP = maxHP

    fun getDamage(damage:Int) {
        HP -= damage-def
    }

    fun getHP(): Int{
        return HP
    }

    fun getMaxHP():Int{
        return maxHP
    }

    fun maxHeal(){
        HP = maxHP
    }

}

class Hero(
    maxHP: Int,
    private val maxMP: Int,
    def: Int,
    damage: Int,
    heroDrawable: Drawable?,
    weaponDrawable: Drawable?):Character(maxHP, def, damage, heroDrawable, weaponDrawable){

    private var MP = maxMP
    val inventory = CharacterInventory()

    fun getMP():Int {
        return MP
    }

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