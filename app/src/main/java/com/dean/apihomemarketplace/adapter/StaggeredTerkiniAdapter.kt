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
import kotlinx.android.synthetic.main.item_staggered_row.view.*

class StaggeredTerkiniAdapter(var context: Context)
    : RecyclerView.Adapter<StaggeredTerkiniAdapter.ViewHolder>() {

    private val listRumah = ArrayList<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                //knp false? karna attachto root value
                .inflate(
                    R.layout.item_staggered_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listRumah.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRumah.get(position))
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(home: DataItem) {
            with(itemView) {
                Glide.with(itemView)
                        .load(home.image)
                        //di requestoption bisa ngatur opacity nya jg
                        .apply(RequestOptions().override(400))
                        .into(iv_staggered_rumah)

                tv_staggered_name.text = home.name
                tv_staggered_address.text = home.desc


                itemView.setOnClickListener { (home) }

            }
        }
    }
}