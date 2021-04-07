package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jawa(
    var id: String,
    var nameJwa: String,
    var addressJwa: String
//        var img_local : Int

) : Parcelable