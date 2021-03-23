package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View.*
import androidx.core.content.ContextCompat
import com.makentoshe.androidgithubcitemplate.Dao.*
import kotlinx.android.synthetic.main.activity_game.*

var db: AppDatabase? = null
var swordDao: SwordDao? = null
var bowDao: BowDao? = null
var staffDao: StaffDao? = null
var characterDao: CharacterDao? = null
var chestplateDao: ChestplateDao? = null
var leggingsDao: LeggingsDao? = null
var helmetDao: HelmetDao? = null
var bootsDao: BootsDao? = null

val NUMBEROFENEMIES = 2


class GameActivity : MyActivity() {

    var game = true
    lateinit var enemy: Character
    lateinit var myHero:Hero
    var k = 0
    var shield_dur = 0
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        db = AppDatabase.getAppDataBase(this)
        swordDao= db?.swordDao()
        bowDao= db?.bowDao()
        staffDao= db?.staffDao()
        characterDao = db?.characterDao()
        chestplateDao = db?.chestplateDao()
        leggingsDao = db?.leggingsDao()
        helmetDao = db?.helmetDao()
        bootsDao = db?.bootsDao()

        exit.setOnClickListener {
            AppDatabase.destroyDataBase()
            finish()
        }

        initEnemies()

        val myClass = intent.getStringExtra("class")
        flag = intent.getBooleanExtra("flag", false)

        randEnemy()

        if (myClass == "warrior") {
            myHero = Hero(
                0,
                300,
                100,
                5,
                20,
                R.drawable.warrior1,
                R.drawable.ic_sword
            )
            swordDao?.insertSword(Sword(
                0,
                -1,
                "Тупой клинок",
                1,
                1,
                1,
                3,
                R.drawable.ic_sword))
            swordDao?.getSwordById(0)?.let { myHero.inventory.carry(it) }
        }else if (myClass == "archer"){
            myHero = Hero(
                0,
                300,
                150,
                3,
                25,
                R.drawable.archer,
                R.drawable.ic_arrow
            )
            weapon.rotation = 0F
            bowDao?.insertBow(Bow(
                0,
                0,
                "Самодельный лук",
                1,
                1,
                1,
                3,
                R.drawable.ic_arrow
            ))
            bowDao?.getBowById(0)?.let{myHero.inventory.carry(it)}
        } else {
            myHero = Hero(
                0,
                250,
                250,
                2,
                30,
                R.drawable.wizard,
                R.drawable.ic_magic
            )
            staffDao?.insertStaff(Staff(
                0,
                0,
                "Старый посох",
                1,
                1,
                1,
                3,
                R.drawable.ic_magic
            ))
            staffDao?.getStaffById(0)?.let { myHero.inventory.carry(it) }
        }

        hero.setImageDrawable(ContextCompat.getDrawable(this, myHero.heroDrawable))
        weapon.setImageDrawable(ContextCompat.getDrawable(this, myHero.weaponDrawable))
        enemy_d.setImageDrawable(ContextCompat.getDrawable(this, enemy.heroDrawable))
        sliz.setImageDrawable(ContextCompat.getDrawable(this, enemy.weaponDrawable))

        myHP.max = myHero.HP
        myHP.progress = myHero.HP
        enemyHP.max = enemy.maxHP
        enemyHP.progress = enemy.HP
        mana.setText("MP ${myHero.MP}")

        attack.setOnClickListener {
            if(game) {
                game = false
                myHero.regenMP()
                mana.setText("MP: ${myHero.MP}")
                attackAnim()
            }
        }

        protect.setOnClickListener {
            if(game){
                game = false
                mana.setText("MP: ${myHero.MP}")
                shieldAnim()
            }
        }

        heal.setOnClickListener {
            if(myHero.MP >= 25)
                if(game) {
                    game = false
                    myHero.heal()
                    myHP.progress = myHero.HP
                    enemyAttackAnim()
                    mana.setText("MP: ${myHero.MP}")
                }
        }

        inventory.setOnClickListener {
            if(game) {
                val intent = Intent(this, InventoryActivity::class.java)
                startActivity(intent)
            }
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
        val i = (1..NUMBEROFENEMIES).random()
        enemy = characterDao?.getCharacterById(i) ?: Character(1,100, 1, 10, R.drawable.enemy, R.drawable.ic_sliz)
    }

    private fun initEnemies() {
        characterDao?.insertCharacter(Character(1,100, 1, 10, R.drawable.enemy, R.drawable.ic_sliz))
        characterDao?.insertCharacter(Character(2,150, 3, 15, R.drawable.enemy2, R.drawable.ic_dirt))
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
                enemy.getAttack(myHero.damage)
                enemyHP.progress = enemy.HP
                if (enemy.HP <= 0) {
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
                    myHP.progress = myHero.HP
                    checkDeath()
                }
            }
        }.start()
    }

    private fun checkDeath() {
        if (myHero.HP <= 0) {
            game = false
            sost.text = "You lose \n Kill count: $k"
            sost.setTextColor(Color.RED)
        }
    }

    private fun enemyAttack() {
        if (shield_dur > 0) {
            myHero.getAttack(enemy.damage - 4)
            shield_dur-=1
        } else
            myHero.getAttack(enemy.damage)
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
        enemy_d.setImageDrawable(ContextCompat.getDrawable(this, enemy.heroDrawable))
        enemy_d.visibility = VISIBLE
        sliz.setImageDrawable(ContextCompat.getDrawable(this, enemy.weaponDrawable))
        enemyHP.max = enemy.maxHP
        enemyHP.progress = enemy.HP
        game = true
    }

    override fun onDestroy() {
        AppDatabase.destroyDataBase()
        super.onDestroy()
    }
}

