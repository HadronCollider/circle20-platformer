package com.makentoshe.androidgithubcitemplate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var k = 0
        var game = true

        exit.setOnClickListener {
            finish()
        }

        var myHero:Character
        val myClass = intent.getStringExtra("class")
        val enemy = Character(100, 1, 10, "enemy")

        if (myClass == "warrior1") {
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.warrior1))
            myHero = Character(300,5,20,myClass)
        } else if (myClass == "archer"){
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.archer))
            myHero = Character(300,3,25,myClass)
        } else{
            hero.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wizard))
            myHero = Character(250,2,30,myClass)
        }

        myHP.max = myHero.getMaxHP()
        myHP.progress = myHero.getHP()
        enemyHP.max = enemy.getMaxHP()
        enemyHP.progress = enemy.getHP()

        attack.setOnClickListener {
            if(game) {
                enemy.getDamage(myHero.damage)
                enemyHP.progress = enemy.getHP()
                game = false
                Thread.sleep(300)
                game = true
                if (enemy.getHP() <= 0) {
                    k++;
                    sost.text = "Kill count: $k"
                    enemy.maxHeal()
                    enemyHP.progress = enemy.getHP()
                } else {
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
    }
}