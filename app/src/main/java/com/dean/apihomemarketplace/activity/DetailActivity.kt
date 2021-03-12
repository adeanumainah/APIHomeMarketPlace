package com.dean.apihomemarketplace.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.fragment.HomeFragment
import com.dean.apihomemarketplace.model.DataItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_see_all_terkini.*
import java.lang.NullPointerException

class DetailActivity : AppCompatActivity() {

    var desc: String? = null

    companion object{
        const val KEY_POPULAR_HOME = "key_popular_home"
    }

    private var home: DataItem? = null

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getStringExtra("key_popular_home").toString()

        var detail:DataItem = Gson().fromJson(data,DataItem::class.java)
//        try {
//            val urlImg:String = "http://192.168.0.109/image/"+detail?.image
//
//            Log.d("Cek DataDi Detail",urlImg)
//            Glide.with(this).load(urlImg).into(iv_image_detail)
//        }catch (e:NullPointerException){
//            e.printStackTrace()
//        }

        val imgUrl = "http://192.168.0.109/apihouse/public/image/"+detail?.image

        Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.houseicon)
            .centerCrop()
            .into(iv_image_detail)


//            home = intent.getParcelableExtra(KEY_POPULAR_HOME)
        tv_lable_name.text = detail?.name.toString()
        tv_lable_address.text = detail?.address.toString()
        tv_lable_price.text = detail?.price.toString()
        tv_detail_desc.text = detail?.desc.toString()
        tv_lable_type.text = detail?.type.toString()
        tv_lable_developer.text = detail?.developer.toString()
        tv_lable_fproperty.text = detail?.propertyFacilities.toString()
        tv_lable_sertifikat.text = detail?.certificate.toString()
        tv_lable_furnished.text = detail?.furnished.toString()
        tv_lable_lantai.text = detail?.numberOfFloors.toString()
        tv_lable_luas.text = detail?.surfaceArea.toString()

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            tv_detail_desc.setText(
//                Html.fromHtml(
//                    desc, Html.FROM_HTML_MODE_COMPACT
//                )
//            )
//        } else {
//            tv_detail_desc.setText(Html.fromHtml(desc))
//        }

        if( desc != null){
            tv_detail_desc.setText(
                Html.fromHtml(
                    desc, Html.FROM_HTML_MODE_COMPACT
                )
            )
        }else{
//            tv_detail_desc.setText(Html.fromHtml(desc))
            Toast.makeText(
                this@DetailActivity,
                "menampilkan detail", Toast.LENGTH_SHORT
            ).show()
        }

        getBack()

    }

    private fun getBack() {
        iv_backstage_detail.setOnClickListener {
            val page = Intent (this, HomeFragment::class.java)
            startActivity(page)
        }

//        iv_backstage_detail.setOnClickListener {
//            val intent = Intent (this, SeeAllPopularActivity::class.java)
//            startActivity(intent)
//        }

    }

    fun panggil(view: View) {
        val nomor = "09667347"
        val panggil = Intent(Intent.ACTION_DIAL)
        panggil.data = Uri.fromParts("tel", nomor, null)
        startActivity(panggil)
    }


}


//        btn_call_us.setOnClickListener {
//            val nomor = "09667347"
//            val panggil = Intent(Intent.ACTION_DIAL)
//            panggil.data = Uri.fromParts("tel", nomor, null)
//            startActivity(panggil)
//        }