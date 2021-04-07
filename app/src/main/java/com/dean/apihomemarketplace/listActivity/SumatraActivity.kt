package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.SumatraAdapter
import com.dean.apihomemarketplace.listAdapter.TanggerangAdapter
import com.dean.apihomemarketplace.model.Sumatra
import com.dean.apihomemarketplace.model.Tanggerang
import kotlinx.android.synthetic.main.activity_sumatra.*
import kotlinx.android.synthetic.main.activity_tanggerang.*

class SumatraActivity : AppCompatActivity() {
    private lateinit var smtAdapter: SumatraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sumatra)

        iv_backstage_smt.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListSmt()
    }

    private fun showRecyclerListSmt() {
        smtAdapter = SumatraAdapter { showSelected(it) }
        smtAdapter.notifyDataSetChanged()
        smtAdapter.setData(getListSmt())
        rv_sumatera.setHasFixedSize(true)
        rv_sumatera.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_sumatera.adapter = smtAdapter
    }

    private fun showSelected(it: Sumatra) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListSmt(): ArrayList<Sumatra> {
        val dataNameSmt = resources.getStringArray(R.array.name_smtr)
        val dataAddressSmt = resources.getStringArray(R.array.address_smtr)
        val dataPhotoSmt = resources.obtainTypedArray(R.array.img_smtr)


        //u meluping
        val listSmt = ArrayList<Sumatra>()

        for (position in dataNameSmt.indices) {
            val smt = Sumatra(
                dataNameSmt[position],
                dataAddressSmt[position],
                dataPhotoSmt.getResourceId(position, -1).toString()
            )
            listSmt.add(smt)
        }
        return listSmt

    }
}