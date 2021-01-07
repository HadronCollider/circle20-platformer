package com.makentoshe.androidgithubcitemplate

class Character(maxHP1: Int,def1: Int, damage1: Int, myClass1: String) {
    private var maxHP = maxHP1
    private var def = def1
    var damage = damage1
    private var myClass = myClass1
    private var HP = maxHP1

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