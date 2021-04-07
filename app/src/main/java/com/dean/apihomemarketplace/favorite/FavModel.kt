package com.dean.apihomemarketplace.favorite

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "fav")
data class FavModel(
        @PrimaryKey
        val id: Int,
        val titlefav: String,
        val addressfav: String,
        val imagefav: String
){

}