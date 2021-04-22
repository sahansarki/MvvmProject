package com.sahan.mvvmproject

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_row.view.*

//Sahan
class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setListData(data : ArrayList<RecyclerData>) {
        this.items = data
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_row,
            parent,false
        )
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size

    class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val tvTitle = view.tvTitle
        val tvDesc = view.tvDesc
        val imageThumb = view.imageThumb

        fun bind(data : RecyclerData) {
            tvTitle.text = data.name
            if(!TextUtils.isEmpty(data.description)) {
                tvDesc.text = data.description
            } else {
                tvDesc.text = "No Description Available."
            }

            val url = data.owner.avatar_url
            Glide.with(imageThumb)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(imageThumb)
        }
    }


}