package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        exit.setOnClickListener {
            finish()
        }

        warrior.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("class", "warrior")
            intent.putExtra("flag", true)
            startActivity(intent)
        }

        wizard.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("class", "wizard")
            intent.putExtra("flag", false)
            startActivity(intent)
        }

        archer.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("class", "archer")
            intent.putExtra("flag", false)
            startActivity(intent)
        }

    }
}