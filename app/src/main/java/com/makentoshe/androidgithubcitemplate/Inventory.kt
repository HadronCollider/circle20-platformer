package com.makentoshe.androidgithubcitemplate

interface Inventory {

    val items: List<Item>

    val capacity: Int

    fun fetch(item: Item): Item?

    fun carry(item: Item)
}

class CharacterInventory(): Inventory {

    override val items = ArrayList<Item>()

    override val capacity: Int = 15

    override fun fetch(item: Item): Item? {
        return if (items.remove(item)) {
            return item
        } else null
    }

    override fun carry(item: Item) {

    }

    fun equip(item: Item) {

    }

}

class ChestInventory(): Inventory {
    override val items = ArrayList<Item>()

    override val capacity: Int = 10

    override fun fetch(item: Item): Item? {
        return if (items.remove(item)) {
            return item
        } else null
    }

    override fun carry(item: Item) {

    }
}