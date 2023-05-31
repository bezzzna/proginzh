package com.example.yourplace.presentation.pointsfragment

import android.os.Bundle
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
import com.example.yourplace.presentation.mapfragment.MapFragment


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

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or UP or DOWN ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
//                val recyclerviewAdapter = recyclerView.adapter as PointRecyclerViewAdapter
//                val fromPosition = viewHolder.adapterPosition
//                val toPosition = target.adapterPosition
//                recyclerviewAdapter.moveItem(fromPosition, toPosition)
//                recyclerviewAdapter.notifyItemMoved(fromPosition,toPosition)

                
                //vm.swapPoint(adapter.currentList[viewHolder.adapterPosition])
                //adapter.notifyItemMoved(viewHolder.oldPosition,viewHolder.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                vm.deletePoint(adapter.currentList[viewHolder.adapterPosition])
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback).attachToRecyclerView(binding.scrollPoints)


        binding.scrollPoints.adapter = adapter
        binding.scrollPoints.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        vm.getList()

    }

}



