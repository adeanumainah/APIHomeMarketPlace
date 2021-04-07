package com.dean.apihomemarketplace.listAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.model.Bogor
import com.dean.apihomemarketplace.model.Jawa
import com.dean.apihomemarketplace.model.Kalimantan
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class JawaAdapter(private val listener: (Jawa) -> Unit)
    : RecyclerView.Adapter<JawaAdapter.ViewHolder>()  {

    private val listJwa = ArrayList<Jawa>()

    fun setData(items: ArrayList<Jawa>){
        listJwa.clear()
        listJwa.addAll(items)
        //mensyncron data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JawaAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_staggered_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: JawaAdapter.ViewHolder, position: Int) {
        holder.bind(listJwa[position], listener)
    }

    override fun getItemCount(): Int = listJwa.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jwa: Jawa, listener: (Jawa) -> Unit) {
            with(itemView) {
//                val urlImg:String = "http://192.168.88.236/apihouse/public/image/"+jkt?.imgJkt
//
//
//                Log.d("Cek DataDi Detail",urlImg)
                Glide.with(itemView)
                    .load("urlImg")
                    .placeholder(R.drawable.house7)
                    .centerCrop()
                    .into(img_list_staggered)

                tv_name_stglist.setText(jwa.nameJwa)
                tv_address_stglist.setText(jwa.addressJwa)


//                Glide.with(itemView.context).load("http://192.168.88.236/apihouse/public/image/")
//                    .apply(RequestOptions().override(300)).into(img_list_staggered)


//                val page = Intent(context, DetailActivity::class.java)
//                page.putExtra(DetailActivity.KEY_POPULAR_HOME, Gson().toJson(tg))
//                context.startActivity(page)

                itemView.setOnClickListener { listener(jwa) }
            }
        }

    }
}