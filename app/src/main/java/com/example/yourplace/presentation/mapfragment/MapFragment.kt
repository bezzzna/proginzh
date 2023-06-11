package com.example.yourplace.presentation.mapfragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yourplace.R
import com.example.yourplace.databinding.FragmentMapBinding
import com.example.yourplace.di.Depenseties
import com.example.yourplace.domain.models.ClassPoint
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.PedestrianCrossing
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Direction
import com.yandex.mapkit.geometry.Geo
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.geometry.Polyline
import com.yandex.mapkit.geometry.PolylineBuilder
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.bicycle.BicycleRouter
import com.yandex.mapkit.transport.bicycle.Route
import com.yandex.mapkit.transport.masstransit.TimeOptions
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.currentCoroutineContext


@Suppress("DEPRECATION")
class MapFragment : Fragment(), DrivingSession.DrivingRouteListener {
    private lateinit var binding: FragmentMapBinding

    private val listX by lazy {
        arguments?.getFloatArray(ARG_X)!!
    }

    private val listY by lazy {
        arguments?.getFloatArray(ARG_Y)!!
    }


    private lateinit var currentPoint: Point





    private var mapObjects:MapObjectCollection? = null
    private var drivingRouter: DrivingRouter? = null
    private var drivingSession:DrivingSession? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val permissionsGranted = ActivityCompat.checkSelfPermission(
            Depenseties.context,
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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showPoint()

        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_mapFragment_to_pointsFragment)
        }
    }

    private fun requestPermission() {
        val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_LOCATION_REQUEST_CODE)
    }

    private fun showPoint() {
        for (i in 0 until listX.count()) {
            binding.mapview.map.mapObjects
                .addPlacemark(Point(listX[i].toDouble(), listY[i].toDouble()), ImageProvider.fromResource(Depenseties.context, R.drawable.place_icon))
        }
        binding.mapview.map.move(
            CameraPosition(Point(listX[listX.count() -1 ].toDouble(), listY[listX.count() - 1].toDouble()), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )

    }


    @SuppressLint("MissingPermission")
    fun showLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient
            .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener {
                currentPoint = Point(it.latitude, it.longitude)
                mapObjects = binding.mapview.map.mapObjects.addCollection()
                drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
                submitRequest()
                binding.mapview.map.move(
                    CameraPosition(currentPoint, 11.0f, 0.0f, 0.0f),
                    Animation(Animation.Type.SMOOTH, 0F),
                    null
                )
                binding.mapview.map.mapObjects.addPlacemark(currentPoint,ImageProvider.fromResource(Depenseties.context, R.drawable.me))
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
                    Toast.makeText(Depenseties.context, "PERMISSION_DENIED", Toast.LENGTH_LONG).show()
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


    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        for(route in p0){
            mapObjects!!.addPolyline(route.geometry)
        }
    }

    override fun onDrivingRoutesError(p0: Error) {
        val errorMsg = "Ошибка!"
        Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    private fun submitRequest() {
        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        binding.mapview.map.mode
        val requestPoints: ArrayList<RequestPoint> = ArrayList()
        requestPoints.add(RequestPoint(currentPoint, RequestPointType.WAYPOINT, null))
        for (i in 0 until listX.count()) {
            val point = Point(listX[i].toDouble(), listY[i].toDouble())
            requestPoints.add(RequestPoint(point, RequestPointType.WAYPOINT, null))
        }
        //drivingSession = TransportFactory.getInstance().createPedestrianRouter()
        drivingSession =  drivingRouter!!.requestRoutes(requestPoints, drivingOptions, vehicleOptions, this)
    }


    companion object {
        private const val PERMISSION_LOCATION_REQUEST_CODE = 101
        private const val ARG_X = "x"
        private const val ARG_Y = "y"


        fun newBundle(points:List<ClassPoint>):Bundle{
            val bundle = Bundle()

            val listX = mutableListOf<Float>()
            val listY = mutableListOf<Float>()

            for (p in points) {
                listX.add(p.coordinateX)
                listY.add(p.coordinateY)
            }
            bundle.putFloatArray(ARG_X, listX.toFloatArray())
            bundle.putFloatArray(ARG_Y, listY.toFloatArray())

            return bundle

        }
    }


}