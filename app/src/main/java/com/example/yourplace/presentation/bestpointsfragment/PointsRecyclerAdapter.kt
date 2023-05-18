package com.example.yourplace.presentation.bestpointsfragment

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.yourplace.R
import com.example.yourplace.data.room.entity.Points

class PointsRecyclerAdapter(private val context: Context) : ListAdapter<Points, PointsRecyclerAdapter.MyViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_test,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(currentList[position].image).into(holder.img)
    }

    class MyViewHolder(private val view: View):ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.img_view)
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Points>() {

        override fun areItemsTheSame(oldItem: Points, newItem: Points): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Points, newItem: Points): Boolean {
            return oldItem == newItem
        }
    }
}