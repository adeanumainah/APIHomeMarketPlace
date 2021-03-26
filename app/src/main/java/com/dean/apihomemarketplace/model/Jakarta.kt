package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Jakarta(
    var id: String,
    var nameJkt: String,
    var addressJkt: String
//        var img_local : Int


) : Parcelable


