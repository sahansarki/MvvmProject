package com.sahan.mvvmproject.network



import com.sahan.mvvmproject.RecyclerList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Sahan
interface RetroService {

    @GET("repositories")// repositories?q=newyork
    fun getDataFromAPI(@Query("q") query: String) : Call<RecyclerList>

}