package com.dean.apihomemarketplace.activity

import android.app.ActionBar
import android.app.PendingIntent.getActivity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.adapter.PropertyPopularAdapter
import com.dean.apihomemarketplace.adapter.StaggeredPopularAdapter
import com.dean.apihomemarketplace.adapter.StaggeredTerkiniAdapter
import com.dean.apihomemarketplace.fragment.HomeFragment
import com.dean.apihomemarketplace.listActivity.*
import com.dean.apihomemarketplace.model.DataItem
import com.dean.apihomemarketplace.model.ResponseHome
import com.dean.apihomemarketplace.model.Tanggerang
import com.dean.apihomemarketplace.utils.ApiService
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_see_all_popular.*
import kotlinx.android.synthetic.main.activity_see_all_terkini.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_staggered_list.*
import kotlinx.android.synthetic.main.item_staggered_row.*
import org.jetbrains.anko.internals.AnkoInternals.getContext
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeeAllPopularActivity : AppCompatActivity() {

    private lateinit var staggeredPopularAdapter: StaggeredPopularAdapter
    private lateinit var searchView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_popular)
        supportActionBar?.hide()

        tv_bekasi_popular.setOnClickListener {
            val intent = Intent(this, BekasiActivity::class.java)
            startActivity(intent)
        }
        tv_jakarta_popular.setOnClickListener {
            val intent = Intent(this, JakartaActivity::class.java)
            startActivity(intent)
        }
        tv_tanggerang_popular.setOnClickListener {
            val intent = Intent(this, TanggerangActivity::class.java)
            startActivity(intent)
        }
        tv_bogor_popular.setOnClickListener {
            val intent = Intent(this, BogorActivity::class.java)
            startActivity(intent)
        }
        tv_depok_popular.setOnClickListener {
            val intent = Intent(this, DepokActivity::class.java)
            startActivity(intent)
        }
        tv_sumatera_popular.setOnClickListener {
            val intent = Intent(this, SumatraActivity::class.java)
            startActivity(intent)
        }
        tv_jawa_popular.setOnClickListener {
            val intent = Intent(this, JawaActivity::class.java)
            startActivity(intent)
        }
        tv_kalimantan_popular.setOnClickListener {
            val intent = Intent(this, KalimantanActivity::class.java)
            startActivity(intent)
        }

        GetDatas()
        getBack()

        staggeredPopularAdapter = this.let { StaggeredPopularAdapter(it) }!!
        rv_all_popular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = staggeredPopularAdapter
        }

        searchView = findViewById(R.id.search_popular)
        searchView.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)


        search_popular.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getBack() {
        iv_backstage_popular.setOnClickListener {
            onBackPressed()
        }
    }


    private fun GetDatas() {

        var loading = ProgressDialog.show(this, "Request Data", "Loading..")
        ApiService.endpoint.getData().enqueue(
                object : Callback<ResponseHome> {
                    override fun onResponse(
                            call: Call<ResponseHome>,
                            response: Response<ResponseHome>
                    ) {
//                        Log.d("Response", "Success" + response.body()?.data)


                        loading.dismiss()
                        Log.d("DATA", "hide loading")
                        if (response.isSuccessful) {
                            val data = response.body()


                            Log.d("DATA", "success")
                            if (data?.status == 200) {
                                Log.d("DATA", "200")
                                if (!data.data.isNullOrEmpty()) {
                                    Log.d("DATA", "ADA")
                                    Log.d("DATA", Gson().toJson(data.data))
                                    staggeredPopularAdapter.setData(data.data!!)
                                }

                            }
                        }
                    }


                    override fun onFailure(call: Call<ResponseHome>, t: Throwable) {
                        Log.d("Response", "Failed : " + t.localizedMessage)
                        loading.dismiss()
                    }




                }

        )


    }


    




//    private fun getListPopular(): ArrayList<Home> {
//        AndroidNetworking.get(com.dean.homemarketplace.utils.APIUtils.API_URL)
//                .setPriority(Priority.MEDIUM).build().getAsJSONArray(object :JSONArrayRequestListener{
//                    override fun onResponse(response: JSONArray) {
//                        for (i in 0 until response.length()){
//                            try {
//                                val dataApiPopular = ProductItem()
//                                val jsonObject = response.getJSONObject(i)
//                                dataApiPopular.name = jsonObject?.getString("name")
//                                dataApiPopular.address = jsonObject.getString("address")
//                                dataApiPopular.price = jsonObject.getInt("price")
//                                dataApiPopular.type = jsonObject.getString("type")
//                                dataApiPopular.propertyFacilities = jsonObject.getString("property_facilities")
//                                dataApiPopular.certificate = jsonObject.getString("certificate")
//                                dataApiPopular.furnished = jsonObject.getString("furnished")
//                                dataApiPopular.numberOfFloors = jsonObject.getInt("number_of_floors")
//                                dataApiPopular.surfaceArea = jsonObject.getString("surface_area")
//
//                                responseItem.add(dataApiPopular)
//                                showRecyclerList()
//                                showRecyclerGrid()
//                            } catch (e : JSONException){
//                                e.printStackTrace()
//                                Toast.makeText(this@SeeAllPopularActivity,
//                                "Gagal menampilkan data", Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    }
//
//                    override fun onError(anError: ANError?) {
//                        Toast.makeText(this@SeeAllPopularActivity,
//                        "gabisa tampil, semangat cari eror nya!!", Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                })
//
//        return listHome
//    }


}