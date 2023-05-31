package com.example.yourplace.presentation.addpointfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentAddPointBinding
import com.example.yourplace.presentation.subcategoryfragment.SubCategoryFragment


class AddPointFragment : Fragment() {

    lateinit var binding: FragmentAddPointBinding
    lateinit var vm: AddPointFragmentViewModel

    private val idSubcategory by lazy {
        arguments?.getInt(ARGUMENT_SUBCATEGORY_ID)!!
    }

    private val idcategory by lazy {
        arguments?.getInt(ARGUMENT_SUBCATEGORY_ID)!!
    }

    //функция создания фрагмента
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPointBinding.inflate(layoutInflater, container, false)
        vm = ViewModelProvider(this)[AddPointFragmentViewModel::class.java]
        return binding.root
    }

    //функция созданного фрагмента
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_addPointFragment_to_subCategoryFragment, SubCategoryFragment.newBundle(idcategory))
        }

        val adapter = AddPointRecyclerAdapter(requireContext())

        adapter.onClick = {
            vm.choisePoint(it)
            findNavController().navigate(R.id.action_addPointFragment_to_pointsFragment)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        vm.getListBySubCategory(idSubcategory)

    }

    companion object {
        private const val ARGUMENT_SUBCATEGORY_ID = "idSubCategory"
        private const val ARGUMENT_CATEGORY_ID = "idCategory"

        fun newBundle(idCategory: Int, idSubCategory: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ARGUMENT_SUBCATEGORY_ID, idSubCategory)
            bundle.putInt(ARGUMENT_CATEGORY_ID, idCategory)
            return bundle
        }
    }


}