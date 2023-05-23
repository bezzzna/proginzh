package com.example.yourplace.presentation.addpointfragment

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.yourplace.R
import com.example.yourplace.domain.models.ClassPoint

class PointsRecyclerAdapter(private val context: Context) : ListAdapter<ClassPoint, PointsRecyclerAdapter.MyViewHolder>(
    DiffUtil()
) {

    lateinit var onClick: (ClassPoint)-> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_add_point1,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Glide.with(context).load(currentList[position].image).into(holder.img)
        holder.text_name.text = currentList[position].name
        holder.text_address.text = currentList[position].address
        holder.img_button.setOnClickListener {
            onClick.invoke(currentList[position])
        }

    }

    class MyViewHolder(private val view: View):ViewHolder(view){
        val img = view.findViewById<ImageView>(R.id.imageView)
        val img_button = view.findViewById<ImageButton>(R.id.img_btn)
        val text_name = view.findViewById<TextView>(R.id.text_name)
        val text_address = view.findViewById<TextView>(R.id.text_address)
    }

    private class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ClassPoint>() {

        override fun areItemsTheSame(oldItem: ClassPoint, newItem: ClassPoint): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClassPoint, newItem: ClassPoint): Boolean {
            return oldItem == newItem
        }
    }
}