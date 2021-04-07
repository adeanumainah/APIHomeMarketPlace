package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bogor(
    var id: String,
    var nameBgr: String,
    var addressBgr: String
//        var img_local : Int

) : Parcelable