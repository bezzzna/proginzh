package com.example.yourplace.presentation.pointsfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.yourplace.R
import com.example.yourplace.domain.models.ClassPoint

class PointRecyclerViewAdapter : ListAdapter<ClassPoint, PointRecyclerViewAdapter.MyViewHolder>(
    DiffUtil2()
) {

    class MyViewHolder(private val view: View) : ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.point_first)
    }


    private class DiffUtil2 : DiffUtil.ItemCallback<ClassPoint>() {
        override fun areItemsTheSame(oldItem: ClassPoint, newItem: ClassPoint): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClassPoint, newItem: ClassPoint): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.form_points,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = currentList[position].name
    }


//    val differ = AsyncListDiffer(this,DiffUtil2())
//    fun moveItem(fromPosition: Int, toPosition: Int) {
//        val list = differ.currentList.toMutableList()
//        val fromItem = list[fromPosition]
//        list.removeAt(fromPosition)
//        if (toPosition < fromPosition) {
//            list.add(toPosition + 1 , fromItem)
//        } else {
//            list.add(toPosition - 1, fromItem)
//        }
//        differ.submitList(list)
//    }



    
}