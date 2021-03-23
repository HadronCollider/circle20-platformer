package com.makentoshe.androidgithubcitemplate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_inventory.*

class InventoryActivity : MyActivity() {

    var i = 0
    lateinit var images: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        i = 0
        images = arrayOf(image0, image1, image2, image3, image4)

        showInventory(swordDao?.getSwordByChestId(0))
        showInventory(bowDao?.getBowByChestId(0))
        showInventory(staffDao?.getStaffByChestId(0))
        showInventory(chestplateDao?.getChestplateByChestId(0))
        showInventory(leggingsDao?.getLeggingsByChestId(0))
        showInventory(helmetDao?.getHelmetByChestId(0))
        showInventory(bootsDao?.getBootsByChestId(0))

        exit.setOnClickListener {
            finish()
        }
    }

    fun showInventory(inventory: List<Item>?) {
        var j = 0;
        if(inventory?.size != null){
            while(j < inventory.size){
                images[i].setImageDrawable(ContextCompat.getDrawable(this, inventory.get(j).draw))
                i++
                j++
            }
        }
    }
}