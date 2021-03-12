package com.dean.apihomemarketplace.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.activity.SeeAllPopularActivity
import com.dean.apihomemarketplace.activity.SeeAllTerkiniActivity
import com.dean.apihomemarketplace.adapter.PropertyPopularAdapter
import com.dean.apihomemarketplace.adapter.ProyekTerkiniAdapter
import com.dean.apihomemarketplace.model.DataItem
import com.dean.apihomemarketplace.model.ResponseHome
import com.dean.apihomemarketplace.utils.ApiService
import com.google.gson.Gson
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.row_listh.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException

class HomeFragment : Fragment() {

    private lateinit var terkiniAdapter: ProyekTerkiniAdapter
    private lateinit var rv_terkini: RecyclerView

    private lateinit var popularAdapter: PropertyPopularAdapter
    private lateinit var rv_popular: RecyclerView

    companion object {
        fun defaultFragment(): HomeFragment {
            val home_fragment = HomeFragment()
            //ngirim ke oncreate
            val bundle = Bundle()
            //arguments default function u ngirim data
            home_fragment.arguments = bundle
            return home_fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_home, container, false)

        rv_terkini = v.findViewById(R.id.rv_terkini)
        rv_popular = v.findViewById(R.id.rv_popular)

        return v
    }

    val imageContentSlider = intArrayOf(
            R.drawable.rumah1,
            R.drawable.rumah2,
            R.drawable.rumah3,
            R.drawable.rumah4

    )

    val imageContentList: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView?) {
            imageView?.setImageResource(imageContentSlider[position])

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carouselView = is_main as CarouselView
        //set yang sudah ditempel
        carouselView.setImageListener(imageContentList)
        //membaca maximum index yang dibaca
        carouselView.setPageCount(imageContentSlider.count())

        GetDatas()

        tv_see_all_terkini.setOnClickListener {
            val intent = Intent(context, SeeAllTerkiniActivity::class.java)
            startActivity(intent)
        }

        tv_see_all_popular.setOnClickListener {
            val intentBest = Intent(context, SeeAllPopularActivity::class.java)
            startActivity(intentBest)
        }

        terkiniAdapter = context?.let { ProyekTerkiniAdapter(it) }!!
        rv_terkini.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = terkiniAdapter
        }

        popularAdapter = context?.let { PropertyPopularAdapter(it) }!!
        rv_popular.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }

//        val data: DataItem? = null
//        val imgUrl = "http://192.168.0.109/apihouse/public/image/"+data?.image
//
//        Glide.with(this)
//            .load(imgUrl)
//            .placeholder(R.drawable.houseicon)
//            .centerCrop()
//            .into(iv_rumah)

    }

    private fun GetDatas() {


        var loading = ProgressDialog.show(context, "Request Data", "Loading..")
        ApiService.endpoint.getData().enqueue(
                object : Callback<ResponseHome> {
                    override fun onResponse(call: Call<ResponseHome>, response: Response<ResponseHome>) {
                        Log.d("Response", "Success" + response.body()?.data)


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
                                    terkiniAdapter.setData(data.data!!)
                                    popularAdapter.setData(data.data!!)
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

    private fun Intent.putExtra(keyPopularHome: String){
        val page = Intent(context, DetailActivity::class.java)
        page.putExtra(DetailActivity.KEY_POPULAR_HOME)
        startActivity(page)
    }
}