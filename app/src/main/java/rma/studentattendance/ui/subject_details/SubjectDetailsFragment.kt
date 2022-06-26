package rma.studentattendance.ui.subject_details

//import rma.studentattendance.di.SubjectRepositoryFactory
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.FragmentSubjectDetailsBinding
import rma.studentattendance.getPictureResource
import rma.studentattendance.getUrlResource
import rma.studentattendance.presentation.SubjectDetailsViewModel
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import rma.studentattendance.R
import rma.studentattendance.ui.MapsActivity


class SubjectDetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentSubjectDetailsBinding


    //private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    private val args: SubjectDetailsFragmentArgs by navArgs()
    private val viewModel: SubjectDetailsViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectDetailsBinding.inflate(layoutInflater)
        binding.fabBack.setOnClickListener { goBack() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subject = viewModel.getSubjectByTitle(args.subjectTitle)
        display(subject)

    }

    private fun display(subject: Subject?) {
        subject?.let {
            binding.apply {
                tvSubjectDetailsTitle.text = "Naziv predmeta: " + subject.title
                tvSubjectDetailsContents.text = "Broj sati predavanja: " + subject.classes.toString()
                tvSubjectDetailsLocation.text = "Lokacija predavanja: " + subject.place.toString()
                ibSubjectDetailsPlace.setBackgroundResource(resources.getPictureResource(subject.place))
                ibSubjectDetailsPlace.setOnClickListener {
                    val action =
                        SubjectDetailsFragmentDirections.actionSubjectDetailsFragmentToMapsFragment()
                    findNavController().navigate(action)
                }
                ibSubjectDetailsPlace1.setOnClickListener {
                    val urlIntent = Intent(
                        "android.intent.action.VIEW",
                        Uri.parse(resources.getUrlResource(subject.place))
                    )
                    startActivity(urlIntent)
                }
            }
        }
    }

    private fun goBack() {
        val action = SubjectDetailsFragmentDirections.actionSubjectDetailsFragmentToStartFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "TasksDetails"
        val TaskIdKey = "TaskId"
        fun create(id: Long): Fragment {
            val fragment = SubjectDetailsFragment()
            return fragment
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val osijek = LatLng(45.55111, 18.69389)
        map.addMarker(MarkerOptions().position(osijek).title("Marker in Osijek"))
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLng(osijek))
    }

    // Define a Place ID.
    val placeId = "INSERT_PLACE_ID_HERE"

    // Specify the fields to return.
    val placeFields = listOf(Place.Field.ID, Place.Field.NAME)

    // Construct a request object, passing the place ID and fields array.
    val request = FetchPlaceRequest.newInstance(placeId, placeFields)

}