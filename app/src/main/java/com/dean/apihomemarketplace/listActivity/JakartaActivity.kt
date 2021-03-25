package com.dean.apihomemarketplace.listActivity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.activity.DetailActivity
import com.dean.apihomemarketplace.listAdapter.JakartaAdapter
import com.dean.apihomemarketplace.model.Jakarta
import kotlinx.android.synthetic.main.activity_jakarta.*

class JakartaActivity : AppCompatActivity() {

    private lateinit var jktAdapter: JakartaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jakarta)

        showRecyclerList()
    }

    private fun showRecyclerList() {
        jktAdapter = JakartaAdapter { showSelected(it) }
        jktAdapter.notifyDataSetChanged()
        jktAdapter.setData(getListJkt())
        rv_jakarta.setHasFixedSize(true)
        rv_jakarta.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_jakarta.adapter = jktAdapter
    }

    private fun showSelected(it: Jakarta) {
//        val page = Intent(this, DetailActivity::class.java)
//        page.putExtra(DetailActivity.KEY_POPULAR_HOME, it)
//        startActivity(page)
    }


    @SuppressLint("Recycle")
    fun getListJkt(): ArrayList<Jakarta> {
        val dataName = resources.getStringArray(R.array.name_jkt)
        val dataAddress = resources.getStringArray(R.array.address_jkt)
        val dataPhoto = resources.obtainTypedArray(R.array.img_jkt)


        //u meluping
        val listJkt = ArrayList<Jakarta>()

        for (position in dataName.indices) {
            val jkt = Jakarta(
                    dataName[position],
                    dataAddress[position],
                dataPhoto.getResourceId(position, -1).toString()
            )
            listJkt.add(jkt)
        }
        return listJkt

    }
//
//    private fun Jakarta(
//        id: String?,
//        nameJkt: String?,
//        addressJkt: String?
//    ): Jakarta {
//        TODO("Not yet implemented")
//    }

//    override fun onClick(p0: View?) {
//        TODO("Not yet implemented")
//    }
}