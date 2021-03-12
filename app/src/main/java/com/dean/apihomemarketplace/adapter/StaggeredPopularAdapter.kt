package com.dean.apihomemarketplace.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.model.DataItem
import kotlinx.android.synthetic.main.item_staggered_list.view.*
import kotlinx.android.synthetic.main.item_staggered_row.view.*
import kotlinx.android.synthetic.main.row_listh.view.*

class StaggeredPopularAdapter(var context: Context)
    : RecyclerView.Adapter<StaggeredPopularAdapter.ViewHolder>() {

    private val listStaggered = ArrayList<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            //knp false? karna attachto root value
            .inflate(
                R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listStaggered.get(position))
    }

    override fun getItemCount(): Int = listStaggered.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(home: DataItem){
            with(itemView){
                Glide.with(itemView)
                        .load(home.image)
                        //di requestoption bisa ngatur opacity nya jg
                        .apply(RequestOptions().override(400))
                        .into(img_list_staggered)

                tv_name_stglist.text = home.name
                tv_address_stglist.text = home.address
//

                itemView.setOnClickListener { (home) }

            }
        }

    }
}