package com.dean.apihomemarketplace.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.model.Bekasi
import com.dean.apihomemarketplace.model.Home
import kotlinx.android.synthetic.main.activity_list_detail.*
import kotlinx.android.synthetic.main.item_staggered_list.*

class ListDetailActivity : AppCompatActivity() {

    companion object{
        const val KEY_POPULAR_LIST = "key_popular_list"
    }

    private var bks: Bekasi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        bks = intent.getParcelableExtra(KEY_POPULAR_LIST)

        tv_name_list.text = bks?.nameBks
        tv_address_list.text = bks?.addressBks


    }


}