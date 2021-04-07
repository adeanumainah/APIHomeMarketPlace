package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Depok(
    var id: String,
    var nameDpk: String,
    var addressDpk: String

//        var img_local : Int

) : Parcelable