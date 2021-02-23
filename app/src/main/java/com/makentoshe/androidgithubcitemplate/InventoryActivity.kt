package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_inventory.*

class InventoryActivity : MyActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val images = arrayOf(image0, image1, image2, image3, image4)

        var i = 0

        val inventory = Gson().fromJson(intent.getStringExtra("inventory"),ArrayList::class.java)

        Log.d("qwertyuiop", inventory.toString())

        /*while(i < inventory.size){
            images[i].setImageDrawable(inventory.get(i).draw)
        }*/

        exit.setOnClickListener {
            finish()
        }
    }
}