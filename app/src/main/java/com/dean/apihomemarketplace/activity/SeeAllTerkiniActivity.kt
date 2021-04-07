package com.dean.apihomemarketplace.activity

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.adapter.StaggeredTerkiniAdapter
import com.dean.apihomemarketplace.listActivity.*
import com.dean.apihomemarketplace.listAdapter.JakartaAdapter
import com.dean.apihomemarketplace.model.Jakarta
import com.dean.apihomemarketplace.model.ResponseHome
import com.dean.apihomemarketplace.utils.ApiService
//import com.dean.homemarketplace.model.ResponseItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_see_all_popular.*
import kotlinx.android.synthetic.main.activity_see_all_terkini.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeeAllTerkiniActivity : AppCompatActivity() {

    private lateinit var staggeredTerkiniAdapter: StaggeredTerkiniAdapter
    private lateinit var searchView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_all_terkini)
        supportActionBar?.hide()

        tv_category_bekasi.setOnClickListener {
            val intent = Intent(this, BekasiActivity::class.java)
            startActivity(intent)
        }
        tv_category_jakarta.setOnClickListener {
            val intent = Intent(this, JakartaActivity::class.java)
            startActivity(intent)
        }
        tv_category_turkish_tanggerang.setOnClickListener {
            val intent = Intent(this, TanggerangActivity::class.java)
            startActivity(intent)
        }
        tv_category_bogor.setOnClickListener {
            val intent = Intent(this, BogorActivity::class.java)
            startActivity(intent)
        }
        tv_category_depok.setOnClickListener {
            val intent = Intent(this, DepokActivity::class.java)
            startActivity(intent)
        }
        tv_category_sumatera.setOnClickListener {
            val intent = Intent(this, SumatraActivity::class.java)
            startActivity(intent)
        }
        tv_category_jawa.setOnClickListener {
            val intent = Intent(this, JawaActivity::class.java)
            startActivity(intent)
        }
        tv_category_kalimantan.setOnClickListener {
            val intent = Intent(this, KalimantanActivity::class.java)
            startActivity(intent)
        }

        showRecyclerGrid()
        getBack()
        getRecyclerList()
//        getDetail()

        searchView = findViewById(R.id.search_terkini)
        searchView.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)


        search_terkini.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }





    private fun getRecyclerList() {
            //mengikat si recyclernya ke dalam list
            val layoutManagerStaggered = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
            rv_all_terkini.layoutManager = layoutManagerStaggered

            //ngeset data diadapter dan diset adapternya disini
            staggeredTerkiniAdapter = StaggeredTerkiniAdapter(this)
            rv_all_terkini.adapter = staggeredTerkiniAdapter
        }

    private fun getBack() {
        iv_backstage_terkini.setOnClickListener {
            onBackPressed()
        }
    }



    private fun showRecyclerGrid() {
        var loading = ProgressDialog.show(this, "Request Data", "Loading..")
        ApiService.endpoint.getData().enqueue(
                object : Callback<ResponseHome> {
                    override fun onResponse(call: Call<ResponseHome>, response: Response<ResponseHome>) {
//                        Log.d("Response", "Success" + response.body()?.data)

                        loading.dismiss()
                        Log.d("DATA", "hide loading")
                        if (response.isSuccessful) {
                            val data = response.body()


                            Log.d("DATA", "success")
                            if(data?.status == 200) {
                                Log.d("DATA", "200")
                                if(!data.data.isNullOrEmpty()){
                                    Log.d("DATA", "ADA")
                                    Log.d("DATA", Gson().toJson(data.data))
                                    staggeredTerkiniAdapter.setData(data.data!!)


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


//    private fun getDetail() {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(home))
//        this.startActivity(page)
//    }













//    private val listHome = ArrayList<Home>()
//    var responseItem: MutableList<ProductItem> = ArrayList()
//    var proyekTerkiniAdapter: ProyekTerkiniAdapter? = null
//

//
//        NetworkConfig().getService()
//                .getUsers()
//                .enqueue(object : Callback<List<ProductItem>> {
//                    override fun onFailure(call: Call<List<ProductItem>>, t: Throwable) {
//                        Toast.makeText(this@SeeAllTerkiniActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
//                        Log.d("TEST", "onFailure: ${t.localizedMessage}")
//                    }
//
//                    override fun onResponse(
//                            call: Call<List<ProductItem>>,
//                            response: Response<List<ProductItem>>
//                    ) {
//                        Log.d("TEST", "onResponse: ${Gson().toJson(response.body())}")
//                        rv_see_all_terkini.adapter = ProyekTerkiniAdapter(response.body())
//                    }
//
//                })
//
//        rv_see_all_terkini.setHasFixedSize(true)
//        rv_see_all_terkini.layoutManager = LinearLayoutManager(this)
//        getListHome()

//
//    private fun getListHome() {
//        AndroidNetworking.get(com.dean.homemarketplace.utils.APIUtils.API_URL)
//            .setPriority(Priority.MEDIUM).build().getAsJSONArray(object : JSONArrayRequestListener {
//                override fun onResponse(response: JSONArray) {
//                    for (i in 0 until response.length()) {
//                        try {
//                            val dataApi = ProductItem()
//                            val jsonObject = response.getJSONObject(i)
//                            dataApi.name = jsonObject.getString("name")
//                            dataApi.address = jsonObject.getString("address")
//                            dataApi.price = jsonObject.getInt("price")
//                            dataApi.type = jsonObject.getString("type")
//                            dataApi.propertyFacilities = jsonObject.getString("property_facilities")
//                            dataApi.certificate = jsonObject.getString("certificate")
//                            dataApi.furnished = jsonObject.getString("furnished")
//                            dataApi.numberOfFloors = jsonObject.getInt("number_of_floors")
//                            dataApi.surfaceArea = jsonObject.getString("surface_area")
//
//                            responseItem.add(dataApi)
////                            showRecyclerList()
////                            showRecyclerGrid()
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                            Toast.makeText(
//                                this@SeeAllTerkiniActivity,
//                                "Gagal menampilkan data",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                }
//
//                override fun onError(anError: ANError?) {
//                    Toast.makeText(
//                        this@SeeAllTerkiniActivity,
//                        "Semangat benerin eror nya cantik!!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//
//            })
//
//    }

//    private fun showRecyclerList() {
//        proyekTerkiniAdapter = ProyekTerkiniAdapter(this@SeeAllTerkiniActivity.listHome)
//        rv_see_all_terkini!!.adapter = proyekTerkiniAdapter
//    }
//
//    private fun showRecyclerGrid() {
//        val layoutManagerStaggered = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
//        rv_see_all_terkini.layoutManager = layoutManagerStaggered
//        rv_see_all_terkini.adapter = ProyekTerkiniAdapter(listHome)
//    }


}