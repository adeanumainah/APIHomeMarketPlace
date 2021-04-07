package com.dean.apihomemarketplace.listAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.model.Depok
import com.dean.apihomemarketplace.model.Sumatra
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class DepokAdapter(private val listener: (Depok) -> Unit)
    : RecyclerView.Adapter<DepokAdapter.ViewHolder>() {

    private val listDpk = ArrayList<Depok>()

    fun setData(items: ArrayList<Depok>){
        listDpk.clear()
        listDpk.addAll(items)
        //mensyncron data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDpk[position], listener)
    }

    override fun getItemCount(): Int = listDpk.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dpk: Depok, listener: (Depok) -> Unit) {
            with(itemView) {
//                val urlImg:String = "http://192.168.88.236/apihouse/public/image/"+jkt?.imgJkt
//
//
//                Log.d("Cek DataDi Detail",urlImg)
                Glide.with(itemView)
                    .load("urlImg")
                    .placeholder(R.drawable.house5)
                    .centerCrop()
                    .into(img_list_staggered)

                tv_name_stglist.setText(dpk.nameDpk)
                tv_address_stglist.setText(dpk.addressDpk)


//                Glide.with(itemView.context).load("http://192.168.88.236/apihouse/public/image/")
//                    .apply(RequestOptions().override(300)).into(img_list_staggered)


//                val page = Intent(context, DetailActivity::class.java)
//                page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(tg))
//                context.startActivity(page)

                itemView.setOnClickListener { listener(dpk) }
            }
        }

    }
}