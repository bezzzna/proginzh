package com.example.yourplace.presentation.choisecategoryfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentCategoriesWantBinding


class ChoiseCategoryFragment : Fragment() {
    lateinit var binding: FragmentCategoriesWantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesWantBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.eating.setOnClickListener {
            findNavController().navigate(R.id.action_choiseCategoryFragment_to_subCategoryFragment)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_subCategoryFragment_to_choiseCategoryFragment)
        }

    }


}