package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tanggerang(
    var id: String,
    var nameTg: String,
    var addressTg: String
//        var img_local : Int

) : Parcelable