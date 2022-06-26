package rma.studentattendance.ui

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import rma.studentattendance.R
import rma.studentattendance.getLanResource
import rma.studentattendance.getLongResource

import rma.studentattendance.model.SubjectPlace

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        val kampusLong = resources.getLongResource(SubjectPlace.Kampus)
        val kampusLat = resources.getLanResource(SubjectPlace.Kampus)
        val trpimirovaLong = resources.getLongResource(SubjectPlace.Trpimirova)
        val trpimirovaLat = resources.getLanResource(SubjectPlace.Trpimirova)
        val kampus = LatLng(kampusLat, kampusLong)
        val trpimirova = LatLng(trpimirovaLat,trpimirovaLong)
        googleMap.addMarker(MarkerOptions().position(kampus).title("Marker in Kampus"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kampus, 13f))
        googleMap.addMarker(MarkerOptions().position(trpimirova).title("Marker in Trpimirova"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(trpimirova, 13F))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}