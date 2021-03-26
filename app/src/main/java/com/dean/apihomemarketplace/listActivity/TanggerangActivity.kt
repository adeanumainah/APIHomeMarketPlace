package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.JakartaAdapter
import com.dean.apihomemarketplace.listAdapter.TanggerangAdapter
import com.dean.apihomemarketplace.model.Jakarta
import com.dean.apihomemarketplace.model.Tanggerang
import kotlinx.android.synthetic.main.activity_jakarta.*
import kotlinx.android.synthetic.main.activity_tanggerang.*

class TanggerangActivity : AppCompatActivity() {

    private lateinit var tgAdapter: TanggerangAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanggerang)

        iv_backstage_tg.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListTg()
    }

    private fun showRecyclerListTg() {
        tgAdapter = TanggerangAdapter { showSelected(it) }
        tgAdapter.notifyDataSetChanged()
        tgAdapter.setData(getListTg())
        rv_tanggerang.setHasFixedSize(true)
        rv_tanggerang.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_tanggerang.adapter = tgAdapter
    }

    private fun showSelected(it: Tanggerang) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListTg(): ArrayList<Tanggerang> {
        val dataNameTg = resources.getStringArray(R.array.name_tg)
        val dataAddressTg = resources.getStringArray(R.array.address_tg)
        val dataPhotoTg = resources.obtainTypedArray(R.array.img_tg)


        //u meluping
        val listTg = ArrayList<Tanggerang>()

        for (position in dataNameTg.indices) {
            val tg = Tanggerang(
                dataNameTg[position],
                dataAddressTg[position],
                dataPhotoTg.getResourceId(position, -1).toString()
            )
            listTg.add(tg)
        }
        return listTg

    }

}