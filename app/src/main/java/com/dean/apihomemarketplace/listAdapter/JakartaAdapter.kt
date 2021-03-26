package com.dean.apihomemarketplace.listAdapter

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
import com.dean.apihomemarketplace.model.Jakarta
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class JakartaAdapter(private val listener: (Jakarta) -> Unit)
    : RecyclerView.Adapter<JakartaAdapter.ViewHolder>() {

    private val listJkt = ArrayList<Jakarta>()

    fun setData(items: ArrayList<Jakarta>){
        listJkt.clear()
        listJkt.addAll(items)
        //mensyncron data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listJkt[position], listener)
    }

    override fun getItemCount(): Int = listJkt.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jkt: Jakarta, listener: (Jakarta) -> Unit) {
            with(itemView) {
//                val urlImg:String = "http://192.168.88.236/apihouse/public/image/"+jkt?.imgJkt
//
//
//                Log.d("Cek DataDi Detail",urlImg)
//                Glide.with(itemView)
//                    .load(urlImg)
//                    .placeholder(R.drawable.houseicon)
//                    .centerCrop()
//                    .into(img_list_staggered)

                tv_name_stglist.setText(jkt.nameJkt)
                tv_address_stglist.setText(jkt.addressJkt)

//            Glide.with(this).load(urlImg).into(iv_image_detail)


//                val page = Intent(context, DetailActivity::class.java)
//                page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(jkt))
//                context.startActivity(page)

                itemView.setOnClickListener { listener(jkt) }
            }

        }

    }
}