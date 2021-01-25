package com.makentoshe.androidgithubcitemplate

import android.graphics.drawable.Drawable

class Character(maxHP1: Int, maxMP1: Int, def1: Int, damage1: Int, myClass1: String, hDraw: Drawable?, wDraw: Drawable?) {
    private var maxHP = maxHP1
    private var def = def1
    var damage = damage1
    private var myClass = myClass1
    private var HP = maxHP1
    private var maxMP = maxMP1
    private var MP = maxMP1
    var heroDrawable: Drawable? = hDraw
    var weaponDrawable: Drawable? = wDraw

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

    fun getMP():Int{
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