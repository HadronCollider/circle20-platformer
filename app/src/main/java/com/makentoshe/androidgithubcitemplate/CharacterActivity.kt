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

        gameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}