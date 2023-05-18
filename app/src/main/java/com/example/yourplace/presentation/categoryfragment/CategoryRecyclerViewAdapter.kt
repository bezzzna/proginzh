package com.example.yourplace.presentation.categoryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.yourplace.R
import com.example.yourplace.domain.models.ClassCategory


class CategoryRecyclerViewAdapter:ListAdapter<ClassCategory,CategoryRecyclerViewAdapter.MyViewHolder>(DiffUtil4()) {

    class MyViewHolder(private val view: View):ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.category_first)
    }

    private class DiffUtil4:DiffUtil.ItemCallback<ClassCategory>(){
        override fun areItemsTheSame(oldItem: ClassCategory, newItem: ClassCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClassCategory, newItem: ClassCategory): Boolean {
            return oldItem == newItem
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_category,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = currentList[position].name
    }
}