package com.dean.apihomemarketplace.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.utils.imageLoad
import kotlinx.android.synthetic.main.item_staggered_list.view.*

class FavoriteRumahAdapter(activity: FragmentActivity) : PagedListAdapter<FavModel, FavoriteRumahAdapter.FavRumahViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavModel>() {

            override fun areItemsTheSame(oldFav: FavModel, newFav: FavModel): Boolean {
                return oldFav.titlefav == newFav.titlefav
            }
            override fun areContentsTheSame(oldFav: FavModel, newFav: FavModel): Boolean {
                return oldFav == newFav
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavRumahViewHolder {
        return FavRumahViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_staggered_list, parent, false))
    }


    override fun onBindViewHolder(holder: FavRumahViewHolder, position: Int) {
        holder.bind((getItem(position)))
    }


    inner class FavRumahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(results: FavModel?) = itemView.run {
            tv_name_stglist.text = results?.titlefav
            tv_address_stglist.text = results?.addressfav
            img_list_staggered.imageLoad(results?.imagefav!!)


            //            cv_item_course.setOnClickListener {
//                val mIntent = Intent(context, DetailActivity::class.java).apply {
//                    putExtra("movies",
//                        DetailModel(results.id,results.original_title,results.poster_path,results.overview)
//                    )
//                }
//                context.startActivity(mIntent)
//            }
        }
    }


}
