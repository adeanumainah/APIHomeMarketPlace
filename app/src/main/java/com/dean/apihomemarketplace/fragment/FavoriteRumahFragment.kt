package com.dean.apihomemarketplace.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dean.apihomemarketplace.favorite.FavoriteRumahAdapter
import com.dean.apihomemarketplace.favorite.FavoriteRumahViewModel
import com.dean.apihomemarketplace.favorite.ViewModelFactory
import com.dean.apihomemarketplace.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import androidx.lifecycle.Observer
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_see_all_popular.*
import java.util.*

class FavoriteRumahFragment : Fragment() {

    val TAG = "FavoriteRumahFragment"
    private var rvCourse: RecyclerView? = null
    private var academyAdapter: FavoriteRumahAdapter? = null
    lateinit var viewModel: FavoriteRumahViewModel


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_see_all_popular, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            //untuk handle jika repository lebih dari 1
            viewModel = obtainViewModel(activity as FragmentActivity)
            //end
            psBar.visibility =View.INVISIBLE
            academyAdapter = FavoriteRumahAdapter(activity as FragmentActivity)
            rvCourse?.layoutManager = LinearLayoutManager(context)
            rvCourse?.setHasFixedSize(true)
            rvCourse?.adapter = academyAdapter



            observeViewModelRequest(viewModel)
        }

    }


    @SuppressLint("FragmentLiveDataObserve")

    private fun observeViewModelRequest(viewModel: FavoriteRumahViewModel) {
    viewModel.getFav().observe(this, Observer { data ->
        Log.d("DATANYA", Gson().toJson(data))
        psBar.visibility = View.VISIBLE
        if (data != null) {
            academyAdapter?.submitList(data)
            academyAdapter?.notifyDataSetChanged()
        }

    })

    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCourse = view.findViewById<View>(R.id.rv_all_popular) as RecyclerView?

    }

    private fun obtainViewModel(activity: FragmentActivity): FavoriteRumahViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(FavoriteRumahViewModel::class.java)
    }


}