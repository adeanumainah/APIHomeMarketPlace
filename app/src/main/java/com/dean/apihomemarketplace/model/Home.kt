package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Home (
        var id : String,
        var name : String,
        var address : String,
        var price : String,
        var desc : String,
        var type : String,
        var developer : String,
        var property_facilities : String,
        var certificate : String,
        var furnished : String,
        var number_of_floors : String,
        var surface_area : String,
        var image : String,
//        var img_local : Int

) : Parcelable


//var id: String,
//var name : String,
//var address: String,
//var furnished: String,
//var developer: String,
//var price: String,
//var surface_area: String,
//var certificate: String,
//var number_of_floors: String,
//var property_facilities: String,
//var type: String,
//var desc: String,
//var image: Int
