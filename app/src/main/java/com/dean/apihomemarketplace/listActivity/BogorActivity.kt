package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.BogorAdapter
import com.dean.apihomemarketplace.listAdapter.TanggerangAdapter
import com.dean.apihomemarketplace.model.Bogor
import com.dean.apihomemarketplace.model.Tanggerang
import kotlinx.android.synthetic.main.activity_bogor.*
import kotlinx.android.synthetic.main.activity_tanggerang.*

class BogorActivity : AppCompatActivity() {

    private lateinit var bgrAdapter: BogorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bogor)

        iv_backstage_bgr.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListBgr()
    }

    private fun showRecyclerListBgr() {
        bgrAdapter = BogorAdapter { showSelected(it) }
        bgrAdapter.notifyDataSetChanged()
        bgrAdapter.setData(getListBgr())
        rv_bogor.setHasFixedSize(true)
        rv_bogor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_bogor.adapter = bgrAdapter
    }

    private fun showSelected(it: Bogor) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListBgr(): ArrayList<Bogor> {
        val dataNameBgr = resources.getStringArray(R.array.name_bgr)
        val dataAddressBgr = resources.getStringArray(R.array.address_bgr)
        val dataPhotoBgr = resources.obtainTypedArray(R.array.img_bgr)


        //u meluping
        val listBgr = ArrayList<Bogor>()

        for (position in dataNameBgr.indices) {
            val bgr = Bogor(
                dataNameBgr[position],
                dataAddressBgr[position],
                dataPhotoBgr.getResourceId(position, -1).toString()
            )
            listBgr.add(bgr)
        }
        return listBgr

    }

}