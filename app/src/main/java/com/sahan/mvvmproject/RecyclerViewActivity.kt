package com.sahan.mvvmproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.sahan.mvvmproject.network.RetroInstance
import com.sahan.mvvmproject.network.RetroService
import com.sahan.mvvmproject.viewmodels.RecyclerActivityViewModel
import kotlinx.android.synthetic.main.activity_recycler_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter : RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    fun createData() {

        // going to listen the live data and then based on the callback its going to update our recycler view adapter
        var viewModel = ViewModelProvider(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {

            if(it != null) {

                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(this@RecyclerViewActivity,"Error in getting data from api", Toast.LENGTH_LONG).show()
            }
        })

        searchButton.setOnClickListener {
            viewModel.makeApiCall(searchBoxId.text.toString())
        }





    }
}