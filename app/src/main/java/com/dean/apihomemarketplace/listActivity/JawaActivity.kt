package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.listAdapter.BogorAdapter
import com.dean.apihomemarketplace.listAdapter.JawaAdapter
import com.dean.apihomemarketplace.model.Bogor
import com.dean.apihomemarketplace.model.Jawa
import com.dean.apihomemarketplace.model.Kalimantan
import kotlinx.android.synthetic.main.activity_bogor.*
import kotlinx.android.synthetic.main.activity_jawa.*

class JawaActivity : AppCompatActivity() {

    private lateinit var jwaAdapter: JawaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jawa)

        iv_backstage_jwa.setOnClickListener {
            onBackPressed()
        }

        showRecyclerListJwa()
    }

    private fun showRecyclerListJwa() {
        jwaAdapter = JawaAdapter { showSelected(it) }
        jwaAdapter.notifyDataSetChanged()
        jwaAdapter.setData(getListJwa())
        rv_jawa.setHasFixedSize(true)
        rv_jawa.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_jawa.adapter = jwaAdapter
    }

    private fun showSelected(it: Jawa) {
//        TODO("Not yet implemented")
    }


    @SuppressLint("Recycle")
    fun getListJwa(): ArrayList<Jawa> {
        val dataNameJwa = resources.getStringArray(R.array.name_jwa)
        val dataAddressJwa = resources.getStringArray(R.array.address_jwa)
        val dataPhotoJwa = resources.obtainTypedArray(R.array.img_jwa)


        //u meluping
        val listJwa = ArrayList<Jawa>()

        for (position in dataNameJwa.indices) {
            val jwa = Jawa(
                dataNameJwa[position],
                dataAddressJwa[position],
                dataPhotoJwa.getResourceId(position, -1).toString()
            )
            listJwa.add(jwa)
        }
        return listJwa

    }


}