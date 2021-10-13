package com.atessam.mycocktails.data

import androidx.room.RoomDatabase

abstract class Database :RoomDatabase() {
    abstract fun cocktailDao():CocktailDao
}