package com.makentoshe.androidgithubcitemplate

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

open class MyActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val decorView: View = window.decorView
        val uiOptions: Int = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        decorView.setSystemUiVisibility(uiOptions)
    }
}