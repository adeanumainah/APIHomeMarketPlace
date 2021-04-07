package com.dean.apihomemarketplace.room

import androidx.paging.DataSource
import androidx.room.*
import com.dean.apihomemarketplace.favorite.FavModel

@Dao
interface FavDao {
    //ganti replace biar bisa ke replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: FavModel)

    @Query("SELECT * from fav ORDER BY id ASC")
    fun getAll(): DataSource.Factory<Int, FavModel>

    @Delete
    fun delete(favModel: FavModel)

    @Update
    fun update(favModel: FavModel)

    @Query("DELETE FROM fav")
    fun deleteAll()

}