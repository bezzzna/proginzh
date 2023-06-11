package com.example.yourplace.presentation.pointsfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentPointsBinding
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.presentation.mapfragment.MapFragment
import java.util.Collections
import java.util.Collections.swap


class PointsFragment : Fragment() {
    lateinit var binding: FragmentPointsBinding

    lateinit var vm: PointsFragmentViewModel


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        binding = FragmentPointsBinding.inflate(layoutInflater,container,false)
        vm = ViewModelProvider(this)[PointsFragmentViewModel::class.java]

        return binding.root



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_pointsFragment_to_mainFragment)
        }
        binding.addPoint.setOnClickListener {
            findNavController().navigate(R.id.action_pointsFragment_to_categoryFragment)
        }
        binding.wayButton.setOnClickListener {
            findNavController().navigate(R.id.action_pointsFragment_to_mapFragment, MapFragment.newBundle(vm.list.value!!))
        }

        val adapter = PointRecyclerViewAdapter()

        val callback = object : ItemTouchHelper.SimpleCallback(UP or DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                vm.list.value?.let { swap(it, viewHolder.adapterPosition,target.adapterPosition) }
                binding.scrollPoints.adapter?.notifyItemMoved(viewHolder.adapterPosition,target.adapterPosition)
                vm.swapPoint(adapter.currentList[viewHolder.adapterPosition], adapter.currentList[target.adapterPosition])

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vm.deletePoint(adapter.currentList[viewHolder.adapterPosition])
                //adapter.notifyItemRemoved(viewHolder.adapterPosition)


            }
        }




        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.scrollPoints)

        binding.scrollPoints.adapter = adapter
        binding.scrollPoints.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        vm.getList()
    }

}



