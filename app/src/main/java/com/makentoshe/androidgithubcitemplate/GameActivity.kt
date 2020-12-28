package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        exit.setOnClickListener {
            finish()
        }



        val myClass = intent.getStringExtra("class")
        if(myClass == "warrior1")
            hero.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.warrior1))
        else if(myClass == "archer")
            hero.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.archer))
        else
            hero.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.wizard))
    }
}