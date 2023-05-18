package com.example.yourplace.presentation.categoryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoryBinding
    lateinit var vm:CategoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        vm = ViewModelProvider(this)[CategoryFragmentViewModel::class.java]
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_categoryFragment_to_pointsFragment)
        }

        val adapter = CategoryRecyclerViewAdapter()

        binding.scrollCategory.adapter = adapter
        binding.scrollCategory.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        vm.getList()

    }




}