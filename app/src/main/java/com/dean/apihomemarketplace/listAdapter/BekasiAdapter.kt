package com.dean.apihomemarketplace.listAdapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.model.Bekasi
import com.dean.apihomemarketplace.model.Tanggerang
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class BekasiAdapter(private val listener: (Bekasi) -> Unit)
    : RecyclerView.Adapter<BekasiAdapter.ViewHolder>(){

    private val listBks = ArrayList<Bekasi>()

    fun setData(items: ArrayList<Bekasi>){
        listBks.clear()
        listBks.addAll(items)
        //mensyncron data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listBks[position], listener)
    }

    override fun getItemCount(): Int = listBks.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bks: Bekasi, listener: (Bekasi) -> Unit) {
            with(itemView) {
//                val urlImg:String = "http://192.168.88.236/apihouse/public/image/"+jkt?.imgJkt
//
//
//                Log.d("Cek DataDi Detail",urlImg)
                Glide.with(itemView)
                    .load("urlImg")
                    .placeholder(R.drawable.house1)
                    .centerCrop()
                    .into(img_list_staggered)

                tv_name_stglist.setText(bks.nameBks)
                tv_address_stglist.setText(bks.addressBks)


//                Glide.with(itemView.context).load("http://192.168.88.236/apihouse/public/image/")
//                    .apply(RequestOptions().override(300)).into(img_list_staggered)

                itemView.setOnClickListener {
                    listener(bks).toString()

//                    Log.d("Bekasi", Gson().toJson(bks))
//
//                    val page = Intent(context, DetailActivity::class.java)
//                    page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(bks))
//                    context.startActivity(page)

                }
            }
        }

    }


}