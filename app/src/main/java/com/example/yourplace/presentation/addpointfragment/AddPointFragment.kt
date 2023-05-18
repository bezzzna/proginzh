package com.example.yourplace.presentation.addpointfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentSeaFoodBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddPointFragment : Fragment() {
    lateinit var binding: FragmentSeaFoodBinding


    //функция создания фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeaFoodBinding.inflate(layoutInflater, container, false)
        return binding.root



    }

    //функция созданного фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_addPointFragment_to_subCategoryFragment)
        }
    }




}