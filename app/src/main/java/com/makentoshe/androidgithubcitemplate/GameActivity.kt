package com.makentoshe.androidgithubcitemplate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : MyActivity() {
    var game = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        exit.setOnClickListener {
            finish()
        }

        val myClass = intent.getStringExtra("class")
        var myHero:Character
        val enemy = Character(100, 1, 10, "enemy")
        var k = 0
        var shield_dur = 0

        if (myClass == "warrior1") {
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.warrior1))
            myHero = Character(300,5,20,myClass)
        } else if (myClass == "archer"){
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.archer))
            myHero = Character(300,3,25,myClass)
            weapon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_arrow))
            weapon.rotation = 0F
        } else{
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wizard))
            myHero = Character(250,2,30,myClass)
            weapon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_magic))
        }

        myHP.max = myHero.getMaxHP()
        myHP.progress = myHero.getHP()
        enemyHP.max = enemy.getMaxHP()
        enemyHP.progress = enemy.getHP()
        val flag = intent.getBooleanExtra("flag", false)

        attack.setOnClickListener {
            if(game) {
                game = false
                weapon.animate().apply {
                    weapon.visibility = VISIBLE
                    duration = 300
                    translationX(750F)
                }.withEndAction {
                    weapon.animate().apply {
                        if(flag) {
                            duration = 300
                            translationX(-50F)
                        }else{
                            weapon.visibility = INVISIBLE
                            duration = 0
                            translationX(0F)
                        }
                    }.withEndAction{
                        weapon.visibility = INVISIBLE
                        enemy.getDamage(myHero.damage)
                        enemyHP.progress = enemy.getHP()
                        if (enemy.getHP() <= 0) {
                            k++
                            sost.text = "Kill count: $k"
                            enemy.maxHeal()
                            enemyHP.progress = enemy.getHP()
                            game = true
                        } else {
                            shield.animate().apply {
                                if(shield_dur > 0) {
                                    shield.visibility = VISIBLE
                                    duration = 750
                                } else duration = 0
                                rotationYBy(720F)
                            }.withEndAction {
                                shield.visibility = INVISIBLE
                                sliz.animate().apply {
                                    if(shield_dur > 0)
                                        duration = 50
                                    else
                                        duration = 400
                                    translationX(0F)
                                }.withEndAction {
                                    sliz.animate().apply {
                                        sliz.visibility = VISIBLE
                                        duration = 300
                                        translationX(-750F)
                                    }.withEndAction {
                                        sliz.animate().apply {
                                            sliz.visibility = INVISIBLE
                                            duration = 0
                                            translationX(0F)
                                        }.withEndAction {
                                            game = true
                                            if (shield_dur > 0) {
                                                myHero.getDamage(enemy.damage - 4)
                                                shield_dur-=1
                                            } else
                                                myHero.getDamage(enemy.damage)
                                            myHP.progress = myHero.getHP()
                                            if (myHero.getHP() <= 0) {
                                                game = false
                                                sost.text = "You lose \n Kill count: $k"
                                                sost.setTextColor(Color.RED)
                                            }
                                        }
                                    }
                                }
                            }.start()
                        }
                    }
                }.start()
            }
        }

        protect.setOnClickListener {
            if(game){
                game = false
                shield.animate().apply {
                    shield.visibility = VISIBLE
                    duration = 750
                    rotationYBy(720F)
                }.withEndAction{
                    shield.visibility = INVISIBLE
                    sliz.animate().apply {
                        duration = 500
                        translationX(0F)
                    }.withEndAction{
                        sliz.animate().apply {
                            sliz.visibility = VISIBLE
                            duration = 300
                            translationX(-750F)
                        }.withEndAction {
                            sliz.animate().apply {
                                sliz.visibility = INVISIBLE
                                duration = 0
                                translationX(0F)
                            }.withEndAction {
                                game = true
                                myHero.getDamage(enemy.damage - 4)
                                shield_dur+=1
                                myHP.progress = myHero.getHP()
                                if (myHero.getHP() <= 0) {
                                    game = false
                                    sost.text = "You lose \n Kill count: $k"
                                    sost.setTextColor(Color.RED)
                                }
                            }
                        }
                    }
                }.start()
            }
        }
    }
}

