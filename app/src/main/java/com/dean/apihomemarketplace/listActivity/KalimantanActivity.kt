package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.BogorAdapter
import com.dean.apihomemarketplace.listAdapter.KalimantanAdapter
import com.dean.apihomemarketplace.model.Bogor
import com.dean.apihomemarketplace.model.Kalimantan
import kotlinx.android.synthetic.main.activity_bogor.*
import kotlinx.android.synthetic.main.activity_kalimantan.*

class KalimantanActivity : AppCompatActivity() {

    private lateinit var klmAdapter: KalimantanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalimantan)

        iv_backstage_klm.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListKlm()
    }

    private fun showRecyclerListKlm() {
        klmAdapter = KalimantanAdapter { showSelected(it) }
        klmAdapter.notifyDataSetChanged()
        klmAdapter.setData(getListKlm())
        rv_kalimantan.setHasFixedSize(true)
        rv_kalimantan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_kalimantan.adapter = klmAdapter
    }

    private fun showSelected(it: Kalimantan) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }

    @SuppressLint("Recycle")
    fun getListKlm(): ArrayList<Kalimantan> {
        val dataNameKlm = resources.getStringArray(R.array.name_klm)
        val dataAddressKlm = resources.getStringArray(R.array.address_klm)
        val dataPhotoKlm = resources.obtainTypedArray(R.array.img_klm)


        //u meluping
        val listKlm = ArrayList<Kalimantan>()

        for (position in dataNameKlm.indices) {
            val klm = Kalimantan(
                dataNameKlm[position],
                dataAddressKlm[position],
                dataPhotoKlm.getResourceId(position, -1).toString()
            )
            listKlm.add(klm)
        }
        return listKlm

    }
}