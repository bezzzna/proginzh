package com.example.yourplace.presentation.pointsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentPointsBinding
import com.example.yourplace.domain.models.ClassPoint


class PointsFragment : Fragment() {
    lateinit var binding: FragmentPointsBinding

    lateinit var vm: PointsFragmentViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

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

        val adapter = PointRecyclerViewAdapter()
        binding.scrollPoints.adapter = adapter
        binding.scrollPoints.layoutManager = LinearLayoutManager(requireContext())
        //binding.scrollPoints.layoutManager = GridLayoutManager(requireContext())


        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        vm.getList()
    }
}



