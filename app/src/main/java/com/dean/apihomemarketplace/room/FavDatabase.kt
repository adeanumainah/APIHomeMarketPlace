package com.dean.apihomemarketplace.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dean.apihomemarketplace.favorite.FavModel

@Database(entities = [FavModel::class], version = 1)
abstract class FavDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao

    companion object {
        private val sLock = Any()
        fun getInstance(context: Context): FavDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavDatabase::class.java,
                        "favorites.db"
                    ).allowMainThreadQueries().build()
                }
                return INSTANCE as FavDatabase
            }
        }

        private var INSTANCE: FavDatabase? = null
    }

}