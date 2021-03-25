package com.dean.apihomemarketplace.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dean.apihomemarketplace.R
import com.dean.apihomemarketplace.adapter.StaggeredPopularAdapter
import com.dean.apihomemarketplace.model.DataItem
import com.dean.apihomemarketplace.model.ResponseHome
import com.dean.apihomemarketplace.utils.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener{

    var searchData: SearchView? = null
    var rv: RecyclerView? = null
    var pb: ProgressBar? = null
    private var staggeredPopularAdapter: StaggeredPopularAdapter? = null
    var dataItem: List<DataItem> = ArrayList<DataItem>()
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchData = findViewById(R.id.searchMovie)
        rv = findViewById(R.id.rvSearch)
        pb = findViewById(R.id.pbSearch)

        searchData!!.setQueryHint("Cari Rumah")
        searchData!!.setOnQueryTextListener(this)
        searchData!!.setIconified(false)
        searchData!!.clearFocus()

        setRv()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {

        return false
    }

    override fun onQueryTextChange(s: String?): Boolean {
        rv!!.visibility = View.GONE
        showLoading(true)

        ApiService.endpoint.searchItem(s)!!.enqueue(object : Callback<ResponseHome?> {
            override fun onResponse(
                call: Call<ResponseHome?>,
                response: Response<ResponseHome?>
            ) {
                showLoading(false)
                rv!!.visibility = View.VISIBLE
                if ((if (response.body() != null) response.body()!!.getResults() else null) != null) {
                    val responseMovie: ResponseHome? = response.body()
                    dataItem = responseMovie!!.getResults() as List<DataItem>
                    staggeredPopularAdapter = StaggeredPopularAdapter(
                        this@SearchActivity
                    )
                    rv!!.adapter = staggeredPopularAdapter
                } else {
                    showLoading(false)
                }
            }

            override fun onFailure(call: Call<ResponseHome?>, t: Throwable) {
                showLoading(false)
            }
        })
        return true
    }

    private fun setRv() {
        if (staggeredPopularAdapter == null) {
//            staggeredPopularAdapter = StaggeredPopularAdapter(this, responseHome)

            staggeredPopularAdapter = this.let { StaggeredPopularAdapter(it) }!!
            rv!!.layoutManager = LinearLayoutManager(this)
            rv!!.adapter = staggeredPopularAdapter
            rv!!.itemAnimator = DefaultItemAnimator()
            rv!!.isNestedScrollingEnabled = true
        } else {
            staggeredPopularAdapter!!.notifyDataSetChanged()
        }
    }


    private fun showLoading(b: Boolean) {
        if (b) {
            pb!!.visibility = View.VISIBLE
        } else {
            pb!!.visibility = View.GONE
        }
    }


}