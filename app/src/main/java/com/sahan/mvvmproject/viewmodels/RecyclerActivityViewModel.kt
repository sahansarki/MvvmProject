package com.sahan.mvvmproject.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sahan.mvvmproject.RecyclerList
import com.sahan.mvvmproject.network.RetroInstance
import com.sahan.mvvmproject.network.RetroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Sahan
class RecyclerActivityViewModel : ViewModel() {
    lateinit var recyclerListData : MutableLiveData<RecyclerList>

    init {
        recyclerListData = MutableLiveData()

    }

    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun makeApiCall (input : String) { // it will inform our activities
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)

        call.enqueue(object : Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if(response.isSuccessful) {
                    //recyclerViewAdapter.setListData(response.body()?.items!!)
                    //recyclerViewAdapter.notifyDataSetChanged()
                    recyclerListData.postValue(response.body())
                } else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {


                recyclerListData.postValue(null)
            }

        })
    }
}