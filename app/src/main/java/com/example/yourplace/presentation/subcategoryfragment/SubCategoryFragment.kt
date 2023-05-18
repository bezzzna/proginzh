package com.example.yourplace.presentation.subcategoryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentFoodBinding


class SubCategoryFragment : Fragment() {
    lateinit var binding: FragmentFoodBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_subCategoryFragment_to_addPointFragment)
        }
        binding.SeaFood.setOnClickListener {
            findNavController().navigate(R.id.action_subCategoryFragment_to_choiseCategoryFragment)
        }
    }



}