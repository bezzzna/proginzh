package com.example.yourplace.presentation.mapfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.yourplace.MAIN
import com.example.yourplace.databinding.FragmentMapBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition


@Suppress("DEPRECATION")
class MapFragment : Fragment() {
    private lateinit var binding: FragmentMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MapKitFactory.setApiKey("a6a574c4-571b-42d1-9e93-62c8772ce8ef")
        MapKitFactory.initialize(MAIN)

        val permissionsGranted = ActivityCompat.checkSelfPermission(
            MAIN,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PERMISSION_GRANTED

        if (!permissionsGranted) {
            requestPermission()
        } else {
            showLocation()
        }

        // Inflate the layout for this fragment
        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    private fun requestPermission() {
        val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(MAIN, permissions, PERMISSION_LOCATION_REQUEST_CODE)
    }

    @SuppressLint("MissingPermission")
    fun showLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(MAIN)

        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener {
                val currentPoint = Point(it.latitude, it.longitude)

                binding.mapview.map.move(
                    CameraPosition(currentPoint, 11.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0F),
                    null
                )
                binding.mapview.map.mapObjects.addPlacemark(currentPoint)
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERMISSION_LOCATION_REQUEST_CODE -> {
                if (grantResults[0] == PERMISSION_GRANTED) {
                    showLocation()
                } else {
                    Toast.makeText(MAIN, "PERMISSION_DENIED", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()

    }
    override fun onStop() {
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()

    }
    companion object {
        private const val PERMISSION_LOCATION_REQUEST_CODE = 101
    }









}