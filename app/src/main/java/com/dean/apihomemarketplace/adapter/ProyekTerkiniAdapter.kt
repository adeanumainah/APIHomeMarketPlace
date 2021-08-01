package com.dean.apihomemarketplace.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.model.DataItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_staggered_list.view.*
import kotlinx.android.synthetic.main.row_listh.view.*


class ProyekTerkiniAdapter(var context: Context)
    : RecyclerView.Adapter<ProyekTerkiniAdapter.ViewHolder>(){

    private var listData: List<DataItem> = ArrayList()

    fun setData(items: List<DataItem>) {
        listData = items
        //buat ngereload/syncronize data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(data: DataItem) {
            with(itemView) {
                tv_name_rumah.text = data.name
                tv_address_rumah.text = data.address

                val urlImg:String = "http://192.168.50.76/apihouse/public/image/"+data.image

                Log.d("Cek DataDi Detail",urlImg)
                Glide.with(itemView)
                        .load(urlImg)
                        .placeholder(R.drawable.houseicon)
                        .centerCrop()
                        .into(iv_row_rumah)

//                Glide.with(context).load("http://192.168.80.139/apihouse/public/image/").centerCrop().into(iv_row_rumah)

                itemView.setOnClickListener {
                    Log.d("Cek DataDi adapter", Gson().toJson(data))

                    val page = Intent(context, DetailActivity::class.java)
                    page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(data))
                    context.startActivity(page)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_listh, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ProyekTerkiniAdapter.ViewHolder, position: Int) {
        holder.bind(listData.get(position))

    }

    override fun getItemCount(): Int = listData.size

}