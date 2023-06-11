package com.example.yourplace.presentation.addpointfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentAddPointBinding
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.example.yourplace.presentation.mapfragment.MapFragment
import com.example.yourplace.presentation.subcategoryfragment.SubCategoryFragment
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yandex.mapkit.geometry.Point


class AddPointFragment : Fragment() {

    lateinit var binding: FragmentAddPointBinding
    lateinit var vm: AddPointFragmentViewModel

    private val idSubcategory by lazy {
        arguments?.getInt(ARGUMENT_SUBCATEGORY_ID)!!
    }

    private val idcategory by lazy {
        arguments?.getInt(ARGUMENT_CATEGORY_ID)!!
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

        val permissionsGranted = ActivityCompat.checkSelfPermission(Depenseties.context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionsGranted) {
            requestPermission()
        } else {
            initCurrentPoint()
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        vm.list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        //vm.getListBySubCategory(idSubcategory)
        vm.getListBySubCategory(idSubcategory)


    }

    private fun requestPermission() {
        val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(requireActivity(), permissions,
            PERMISSION_LOCATION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERMISSION_LOCATION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initCurrentPoint()
                } else {
                    Toast.makeText(Depenseties.context, "PERMISSION_DENIED", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }


    @SuppressLint("MissingPermission")
    private fun initCurrentPoint(){
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener {
                vm.currentPoint = Point(it.latitude, it.longitude)
            }
    }

    companion object {
        private const val ARGUMENT_SUBCATEGORY_ID = "idSubCategory"
        private const val ARGUMENT_CATEGORY_ID = "idCategory"

        private const val PERMISSION_LOCATION_REQUEST_CODE = 101

        fun newBundle(idCategory: Int, idSubCategory: Int): Bundle {
            val bundle = Bundle()
            bundle.putInt(ARGUMENT_SUBCATEGORY_ID, idSubCategory)
            bundle.putInt(ARGUMENT_CATEGORY_ID, idCategory)
            return bundle
        }
    }


}