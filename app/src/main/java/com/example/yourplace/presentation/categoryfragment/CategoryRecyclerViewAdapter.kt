package com.example.yourplace.presentation.categoryfragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.yourplace.R
import com.example.yourplace.di.Depenseties.context
import com.example.yourplace.domain.models.ClassCategory


class CategoryRecyclerViewAdapter:ListAdapter<ClassCategory,CategoryRecyclerViewAdapter.MyViewHolder>(DiffUtil4()) {

    lateinit var onClick: (ClassCategory)-> Unit

    class MyViewHolder(private val view: View):ViewHolder(view){

        val name = view.findViewById<TextView>(R.id.category_first)
        val imgButton = view.findViewById<ImageButton>(R.id.img_button)
        val img = view.findViewById<ImageView>(R.id.imageViewCategory)



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
        Glide.with(context).load(currentList[position].image).into(holder.img)
        holder.name.text = currentList[position].name
        holder.imgButton.setOnClickListener {
            Log.d("CategoryRecyclerViewAdapter", "Click")
            onClick.invoke(currentList[position])
        }
    }
}