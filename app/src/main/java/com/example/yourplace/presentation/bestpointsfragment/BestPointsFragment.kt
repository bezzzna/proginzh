package com.example.yourplace.presentation.bestpointsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.databinding.FragmentTestBinding


class BestPointsFragment : Fragment() {

    lateinit var binding: FragmentTestBinding
    lateinit var viewModel: MyViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pointsRecyclerAdapter = PointsRecyclerAdapter(requireContext())

        viewModel.list.observe(viewLifecycleOwner) {
            pointsRecyclerAdapter.submitList(it)
            Log.d("TEST", it.toString())
        }

        viewModel.getList()

        binding.view1.adapter = pointsRecyclerAdapter
        binding.view1.layoutManager = LinearLayoutManager(requireContext())

    }


}

