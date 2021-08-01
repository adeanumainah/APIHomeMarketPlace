package com.dean.apihomemarketplace.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.favorite.FavModel
import com.dean.apihomemarketplace.favorite.ViewModelFactory
import com.dean.apihomemarketplace.fragment.HomeFragment
import com.dean.apihomemarketplace.model.Bekasi
import com.dean.apihomemarketplace.model.DataItem
import com.dean.apihomemarketplace.model.DetailModel
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_see_all_terkini.*
import java.lang.NullPointerException

class DetailActivity : AppCompatActivity() {

    var desc: String? = null
    val TAG = "DetailActivity"
    lateinit var detailViewModel: DetailViewModel

    private val result by lazy {
        intent.getParcelableExtra<DetailModel>("home")
    }

    companion object{
        const val KEY_POPULAR_HOME = "key_popular_home"
    }

    private var home: DataItem? = null

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailViewModel = obtainViewModel(this)

        val data = intent.getStringExtra("key_popular_home").toString()

        var detail:DataItem = Gson().fromJson(data,DataItem::class.java)
        try {
            val urlImg:String = "http://192.168.50.76/apihouse/public/image/"+detail.image

            Log.d("Cek DataDi Detail",urlImg)
            Glide.with(this)
                .load(urlImg)
                .placeholder(R.drawable.houseicon)
                .centerCrop()
                .into(iv_image_detail)
        }catch (e:NullPointerException){
            e.printStackTrace()
            Log.d("Cek DataDi Detail","null")
        }



//            home = intent.getParcelableExtra(KEY_POPULAR_HOME)
        tv_lable_name.text = detail.name.toString()
        tv_lable_address.text = detail.address.toString()
        tv_lable_price.text = detail.price.toString()
        tv_detail_desc.text = detail.desc.toString()
        tv_lable_type.text = detail.type.toString()
        tv_lable_developer.text = detail.developer.toString()
        tv_lable_fproperty.text = detail.propertyFacilities.toString()
        tv_lable_sertifikat.text = detail.certificate.toString()
        tv_lable_furnished.text = detail.furnished.toString()
        tv_lable_lantai.text = detail.numberOfFloors.toString()
        tv_lable_luas.text = detail.surfaceArea.toString()



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
            onBackPressed()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menufavorite, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_favorite -> observeViewModelRequest(detailViewModel,
                FavModel(result?.id ?: 0, result?.name ?: "", result?.address ?: "", result?.image ?: "")
            )

        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeViewModelRequest(detailViewModel: DetailViewModel, favModel: FavModel) {
        detailViewModel.saveFav(favModel).observe(this, Observer{ data ->
            if (data !=null) {
                Log.i(TAG, "datanya = $data")
                Toasty.success(applicationContext, "Save Success", Toast.LENGTH_SHORT).show()
            } else {
                Toasty.error(applicationContext, "Failed Save", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)

    }




    fun panggil() {
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