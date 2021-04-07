package com.dean.apihomemarketplace.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.model.DataItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_staggered_list.view.*
import kotlinx.android.synthetic.main.item_staggered_row.view.*
import kotlinx.android.synthetic.main.row_listh.view.*

class StaggeredPopularAdapter(var context: Context)
    : RecyclerView.Adapter<StaggeredPopularAdapter.ViewHolder>() {

    private var listRumah: List<DataItem> = ArrayList()

    fun setData(items: List<DataItem>) {
        listRumah = items
        //buat ngereload/syncronize data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(data: DataItem){
            with(itemView){
//                Glide.with(itemView)
//                    .load(data.image)
//                    //di requestoption bisa ngatur opacity nya jg
//                    .apply(RequestOptions().override(400))
//                    .into(img_list_staggered)

                val urlImg:String = "http://192.168.42.36/apihouse/public/image/"+data.image

                Log.d("Cek DataDi Detail",urlImg)
                Glide.with(itemView)
                        .load(urlImg)
                        .placeholder(R.drawable.houseicon)
                        .centerCrop()
                        .into(img_list_staggered)

                tv_name_stglist.text = data.name
                tv_address_stglist.text = data.address
//
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
        val view = LayoutInflater.from(parent.context)
            //knp false? karna attach to root value
            .inflate(
                R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRumah.get(position))
    }

    override fun getItemCount(): Int = listRumah.size

}