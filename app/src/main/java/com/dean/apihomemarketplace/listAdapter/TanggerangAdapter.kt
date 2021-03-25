package com.dean.apihomemarketplace.listAdapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.model.Jakarta
import com.dean.apihomemarketplace.model.Tanggerang
import com.google.gson.Gson
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class TanggerangAdapter(private val listener: (Tanggerang) -> Unit)
    : RecyclerView.Adapter<TanggerangAdapter.ViewHolder>() {

    private val listTg = ArrayList<Tanggerang>()

    fun setData(items: ArrayList<Tanggerang>){
        listTg.clear()
        listTg.addAll(items)
        //mensyncron data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTg[position], listener)
    }

    override fun getItemCount(): Int = listTg.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tg: Tanggerang, listener: (Tanggerang) -> Unit) {
            with(itemView) {
                Glide.with(itemView.context).load("http://192.168.88.236/apihouse/public/image/")
                    .apply(RequestOptions().override(300)).into(img_list_staggered)

                tv_name_stglist.setText(tg.nameTg)
                tv_address_stglist.setText(tg.addressTg)

//                val page = Intent(context, DetailActivity::class.java)
//                page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(tg))
//                context.startActivity(page)

                itemView.setOnClickListener { listener(tg) }
            }
        }

    }
}