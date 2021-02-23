package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View.*
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.collections.ArrayList
import com.google.gson.Gson


class GameActivity : MyActivity() {

    var game = true
    lateinit var enemies: Array<Character>
    lateinit var enemy: Character
    lateinit var myHero:Hero
    var k = 0
    var shield_dur = 0
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        exit.setOnClickListener {
            finish()
        }

        initEnemies()

        val myClass = intent.getStringExtra("class")
        flag = intent.getBooleanExtra("flag", false)

        randEnemy()

        if (myClass == "warrior") {
            myHero = Hero(
                300,
                100,
                5,
                20,
                ContextCompat.getDrawable(this, R.drawable.warrior1),
                ContextCompat.getDrawable(this, R.drawable.ic_sword)
            )
            myHero.inventory.carry(Sword(
                "Тупой клинок",
                1,
                1,
                1,
                3,
                ContextCompat.getDrawable(this, R.drawable.ic_sword))
            )
        }else if (myClass == "archer"){
            myHero = Hero(
                300,
                150,
                3,
                25,
                ContextCompat.getDrawable(this, R.drawable.archer),
                ContextCompat.getDrawable(this, R.drawable.ic_arrow)
            )
            weapon.rotation = 0F
            myHero.inventory.carry(Bow(
                "Самодельный лук",
                1,
                1,
                1,
                3,
                ContextCompat.getDrawable(this, R.drawable.ic_arrow))
            )
        } else {
            myHero = Hero(
                250,
                250,
                2,
                30,
                ContextCompat.getDrawable(this, R.drawable.wizard),
                ContextCompat.getDrawable(this, R.drawable.ic_magic)
            )
            myHero.inventory.carry(Sword(
                "Старый посох",
                1,
                1,
                1,
                3,
                ContextCompat.getDrawable(this, R.drawable.ic_magic))
            )
        }

        hero.setImageDrawable(myHero.heroDrawable)
        weapon.setImageDrawable(myHero.weaponDrawable)
        enemy_d.setImageDrawable(enemy.heroDrawable)
        sliz.setImageDrawable(enemy.weaponDrawable)

        myHP.max = myHero.getMaxHP()
        myHP.progress = myHero.getHP()
        enemyHP.max = enemy.getMaxHP()
        enemyHP.progress = enemy.getHP()
        mana.setText("MP ${myHero.getMP()}")

        attack.setOnClickListener {
            if(game) {
                game = false
                myHero.regenMP()
                mana.setText("MP: ${myHero.getMP()}")
                attackAnim()
            }
        }

        protect.setOnClickListener {
            if(game){
                game = false
                mana.setText("MP: ${myHero.getMP()}")
                shieldAnim()
            }
        }

        heal.setOnClickListener {
            if(myHero.getMP() >= 25)
                if(game) {
                    game = false
                    myHero.heal()
                    myHP.progress = myHero.getHP()
                    enemyAttackAnim()
                    mana.setText("MP: ${myHero.getMP()}")
                }
        }

        inventory.setOnClickListener {
            val intent = Intent(this, InventoryActivity::class.java)
            val str: String = Gson().toJson(myHero.inventory)
            intent.putExtra("inventory", str)
            startActivity(intent)
        }
    }

    private fun shieldAnim() {
        shield.animate().apply {
            shield.visibility = VISIBLE
            duration = 750
            rotationYBy(720F)
        }.withEndAction{
            shield.visibility = INVISIBLE
            shield_dur+=2
            enemyAttackAnim()
        }.start()
    }

    private fun randEnemy() {
        val i = (0..(enemies.size-1)).random()
        enemy = enemies[i]
    }

    private fun initEnemies() {
        val enemies0 = Character(100, 1, 10, ContextCompat.getDrawable(this, R.drawable.enemy), ContextCompat.getDrawable(this, R.drawable.ic_sliz))
        val enemies1 = Character(150, 3, 15, ContextCompat.getDrawable(this, R.drawable.enemy2), ContextCompat.getDrawable(this, R.drawable.ic_dirt))

        enemies = arrayOf(enemies0, enemies1)
    }

    private fun attackAnim(){
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
                    duration = 100
                    translationX(0F)
                }
            }.withEndAction{
                weapon.visibility = INVISIBLE
                enemy.getDamage(myHero.damage)
                enemyHP.progress = enemy.getHP()
                if (enemy.getHP() <= 0) {
                    killEnemy()
                } else {
                    shield.animate().apply {
                        if(shield_dur > 0) {
                            shield.visibility = VISIBLE
                            duration = 750
                        } else duration = 0
                        rotationYBy(720F)
                    }.withEndAction {
                        shield.visibility = INVISIBLE
                        enemyAttackAnim()
                    }.start()
                }
            }
        }.start()
    }

    private fun enemyAttackAnim() {
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
                    enemyAttack()
                    myHP.progress = myHero.getHP()
                    checkDeath()
                }
            }
        }.start()
    }

    private fun checkDeath() {
        if (myHero.getHP() <= 0) {
            game = false
            sost.text = "You lose \n Kill count: $k"
            sost.setTextColor(Color.RED)
        }
    }

    private fun enemyAttack() {
        if (shield_dur > 0) {
            myHero.getDamage(enemy.damage - 4)
            shield_dur-=1
        } else
            myHero.getDamage(enemy.damage)
    }

    private fun killEnemy() {
        k++
        sost.text = "Kill count: $k"
        killEnemyAnim()
    }

    private fun killEnemyAnim() {
        enemy_d.animate().apply {
            duration = 200
            scaleX(0F)
            scaleY(10F)
        }.withEndAction {
            enemy_d.visibility = INVISIBLE
            enemy_d.animate().apply {
                duration = 200
                scaleX(1F)
                scaleY(1F)
            }.withEndAction {
                newEnemy()
            }
        }
    }

    private fun newEnemy() {
        randEnemy()
        enemy.maxHeal()
        enemy_d.setImageDrawable(enemy.heroDrawable)
        enemy_d.visibility = VISIBLE
        sliz.setImageDrawable(enemy.weaponDrawable)
        enemyHP.max = enemy.getMaxHP()
        enemyHP.progress = enemy.getHP()
        game = true
    }
}

