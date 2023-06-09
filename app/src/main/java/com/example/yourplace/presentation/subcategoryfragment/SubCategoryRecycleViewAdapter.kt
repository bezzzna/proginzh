package com.example.yourplace.presentation.subcategoryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.yourplace.R
import com.example.yourplace.domain.models.ClassCategory
import com.example.yourplace.domain.models.ClassSubCategory

class SubCategoryRecycleViewAdapter:ListAdapter<ClassSubCategory,SubCategoryRecycleViewAdapter.MyViewHolder>(DiffUtil3()) {

    lateinit var onClick: (ClassSubCategory)-> Unit

    class MyViewHolder(private val view: View):ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.subCategory_first)
        val imgButton = view.findViewById<ImageButton>(R.id.img_button)
    }

    private class DiffUtil3 : DiffUtil.ItemCallback<ClassSubCategory>() {

        override fun areItemsTheSame(oldItem: ClassSubCategory, newItem: ClassSubCategory): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClassSubCategory, newItem: ClassSubCategory): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_subcategory,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = currentList[position].name
        holder.imgButton.setOnClickListener {
            onClick.invoke(currentList[position])
        }
    }
}