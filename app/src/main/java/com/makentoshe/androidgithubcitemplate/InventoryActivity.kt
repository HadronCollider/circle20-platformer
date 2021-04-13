package com.makentoshe.androidgithubcitemplate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_inventory.*

class InventoryActivity : MyActivity() {

    var i = 0
    lateinit var images: Array<ImageView>

    class Save(val id:Int, val clas:Class<*>)

    val saves:MutableList<Save> = mutableListOf()
    var index = 0
    var k = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        i = 0
        images = arrayOf(image0, image1, image2, image3, image4)

        k = intent.getIntExtra("id",0)
        show()

        for(i in 0..images.size-1){
            val menu = PopupMenu(this,images[i])
            menu.menu.add(i,0,1,"Throw")
            menu.menu.add(i,1,2,"Equip")
            menu.setOnMenuItemClickListener {
                when(it.itemId){
                    0 -> {
                        if(saves.get(i).clas == Sword::class.java){
                            val item = swordDao?.getSwordById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                swordDao?.insertSword(item)
                        }else if(saves.get(i).clas == Bow::class.java){
                            val item = bowDao?.getBowById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                bowDao?.insertBow(item)
                        }else if(saves.get(i).clas == Staff::class.java){
                            val item = staffDao?.getStaffById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                staffDao?.insertStaff(item)
                        }else if(saves.get(i).clas == Chestplate::class.java){
                            val item = chestplateDao?.getChestplateById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                chestplateDao?.insertChestplate(item)
                        }else if(saves.get(i).clas == Leggings::class.java){
                            val item = leggingsDao?.getLeggingsById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                leggingsDao?.insertLeggings(item)
                        }else if(saves.get(i).clas == Helmet::class.java){
                            val item = helmetDao?.getHelmetById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                helmetDao?.insertHelmet(item)
                        }else if(saves.get(i).clas == Boots::class.java){
                            val item = bootsDao?.getBootsById(saves.get(i).id)
                            item?.inventoryId = -1
                            if (item != null)
                                bootsDao?.insertBoots(item)
                        }
                        repaint()
                        true
                    }
                    1 -> {
                        Log.d("str","equip")
                        true
                    }
                    else -> false
                }
            }
            images[i].setOnClickListener {
                if(images[i].drawable != null)
                    menu.show()
            }
        }

        exit.setOnClickListener {
            finish()
        }
    }

    fun show(){
        showInventory(swordDao?.getSwordByChestId(k))
        showInventory(bowDao?.getBowByChestId(k))
        showInventory(staffDao?.getStaffByChestId(k))
        showInventory(chestplateDao?.getChestplateByChestId(k))
        showInventory(leggingsDao?.getLeggingsByChestId(k))
        showInventory(helmetDao?.getHelmetByChestId(k))
        showInventory(bootsDao?.getBootsByChestId(k))
    }

    fun showInventory(inventory: List<Item>?) {
        var j = 0;
        if(inventory?.size != null){
            while(j < inventory.size){
                images[i].setImageDrawable(ContextCompat.getDrawable(this, inventory.get(j).draw))
                saves.add(index,Save(inventory.get(j).id,inventory.get(j)::class.java))
                index++
                i++
                j++
            }
        }
    }

    fun repaint(){
        for(i in 0..images.size-1){
            images[i].setImageDrawable(null)
        }
        show()
    }
}