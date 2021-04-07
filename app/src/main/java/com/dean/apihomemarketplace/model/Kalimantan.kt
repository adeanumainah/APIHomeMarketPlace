package com.dean.apihomemarketplace.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Kalimantan(
    var id: String,
    var nameKlm: String,
    var addressKlm: String
//        var img_local : Int

) : Parcelable