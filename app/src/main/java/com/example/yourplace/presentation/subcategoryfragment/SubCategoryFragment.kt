package com.example.yourplace.presentation.subcategoryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentSubcategoryBinding
import com.example.yourplace.presentation.pointsfragment.PointsFragmentViewModel


class SubCategoryFragment : Fragment() {
    lateinit var binding: FragmentSubcategoryBinding
    lateinit var vm: SubCategoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubcategoryBinding.inflate(layoutInflater, container, false)

        vm = ViewModelProvider(this)[SubCategoryFragmentViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_subCategoryFragment_to_categoryFragment)
        }

        val adapter = SubCategoryRecycleViewAdapter()

        binding.scrollSubCategory.adapter = adapter
        binding.scrollSubCategory.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        vm.getList()

    }









}