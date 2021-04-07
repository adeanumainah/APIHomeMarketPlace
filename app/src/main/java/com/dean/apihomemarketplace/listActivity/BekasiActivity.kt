package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.ListDetailActivity
import com.dean.apihomemarketplace.adapter.StaggeredPopularAdapter
import com.dean.apihomemarketplace.listAdapter.BekasiAdapter
import com.dean.apihomemarketplace.listAdapter.TanggerangAdapter
import com.dean.apihomemarketplace.model.Bekasi
import com.dean.apihomemarketplace.model.Tanggerang
import kotlinx.android.synthetic.main.activity_bekasi.*
import kotlinx.android.synthetic.main.activity_tanggerang.*

class BekasiActivity : AppCompatActivity() {


    private lateinit var bksAdapter: BekasiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bekasi)

        iv_backstage_bks.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListBks()
    }

    private fun showRecyclerListBks() {
        bksAdapter = BekasiAdapter { showSelected(it) }
        bksAdapter.notifyDataSetChanged()
        bksAdapter.setData(getListBks())
        rv_bekasi.setHasFixedSize(true)
        rv_bekasi.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_bekasi.adapter = bksAdapter
    }

    private fun showSelected(it: Bekasi) {
        val page = Intent(this, ListDetailActivity::class.java)
        page.putExtra(ListDetailActivity.KEY_POPULAR_LIST, it)
        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListBks(): ArrayList<Bekasi> {
        val dataNameBks = resources.getStringArray(R.array.name_bks)
        val dataAddressBks = resources.getStringArray(R.array.address_bks)
        val dataPhotoBks = resources.obtainTypedArray(R.array.img_bks)


        //u meluping
        val listBks = ArrayList<Bekasi>()

        for (position in dataNameBks.indices) {
            val bks = Bekasi(
                dataNameBks[position],
                dataAddressBks[position],
                dataPhotoBks.getResourceId(position, -1).toString()
            )
            listBks.add(bks)
        }
        return listBks

    }

}