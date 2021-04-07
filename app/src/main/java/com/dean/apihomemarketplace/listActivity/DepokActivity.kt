package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.DepokAdapter
import com.dean.apihomemarketplace.listAdapter.TanggerangAdapter
import com.dean.apihomemarketplace.model.Depok
import com.dean.apihomemarketplace.model.Tanggerang
import kotlinx.android.synthetic.main.activity_depok.*
import kotlinx.android.synthetic.main.activity_tanggerang.*

class DepokActivity : AppCompatActivity() {

    private lateinit var dpkAdapter: DepokAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depok)

        iv_backstage_dpk.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListDpk()
    }

    private fun showRecyclerListDpk() {
        dpkAdapter = DepokAdapter { showSelected(it) }
        dpkAdapter.notifyDataSetChanged()
        dpkAdapter.setData(getListDpk())
        rv_depok.setHasFixedSize(true)
        rv_depok.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_depok.adapter = dpkAdapter
    }

    private fun showSelected(it: Depok) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListDpk(): ArrayList<Depok> {
        val dataNameDpk = resources.getStringArray(R.array.name_dpk)
        val dataAddressDpk = resources.getStringArray(R.array.address_dpk)
        val dataPhotoDpk = resources.obtainTypedArray(R.array.img_dpk)


        //u meluping
        val listDpk = ArrayList<Depok>()

        for (position in dataNameDpk.indices) {
            val dpk = Depok(
                dataNameDpk[position],
                dataAddressDpk[position],
                dataPhotoDpk.getResourceId(position, -1).toString()
            )
            listDpk.add(dpk)
        }
        return listDpk

    }
}