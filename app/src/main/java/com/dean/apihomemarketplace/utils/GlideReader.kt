package com.dean.apihomemarketplace.utils


import android.widget.ImageView
import com.bumptech.glide.Glide

internal fun ImageView.imageLoad(imagefav: String) {

    Glide.with(this.context).load("http://192.168.88.237/apihouse/public/image/$imagefav").centerCrop().into(this)
}


