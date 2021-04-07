package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sumatra(
    var id: String,
    var nameSmt: String,
    var addressSmt: String
//        var img_local : Int

) : Parcelable