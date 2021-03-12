package com.dean.apihomemarketplace.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.model.DataItem
import com.google.gson.Gson
//import com.dean.homemarketplace.model.ResponseItem
import kotlinx.android.synthetic.main.row_listh.view.*

class PropertyPopularAdapter(var context: Context)
    : RecyclerView.Adapter<PropertyPopularAdapter.ViewHolder>(){

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
                Glide.with(context).load(data.image).centerCrop().into(iv_rumah)
                itemView.setOnClickListener {
                    Log.d("Cek DataDi adapter", Gson().toJson(data))

                    val page = Intent(context, DetailActivity::class.java)
                    page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(data))
                    context.startActivity(page)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyPopularAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_listh, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: PropertyPopularAdapter.ViewHolder, position: Int) {
        holder.bind(listData.get(position))

    }

    override fun getItemCount(): Int = listData.size

}