package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bekasi(
    var id: String,
    var nameBks: String,
    var addressBks: String
//        var img_local : Int

) : Parcelable