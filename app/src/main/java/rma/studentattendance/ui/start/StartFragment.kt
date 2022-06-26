package rma.studentattendance.ui.start

//import rma.studentattendance.di.SubjectRepositoryFactory
//import rma.studentattendance.ui.subject_list.SubjectAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import rma.studentattendance.databinding.FragmentStartBinding
import kotlin.system.exitProcess

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        binding.bSaveSubject.setOnClickListener { showCreateNewSubjectFragment() }
        binding.bListSubject.setOnClickListener { showListSubjectFragment() }
        binding.bListAttendance.setOnClickListener { showListAttendanceFragment() }
        binding.bAddAttendance.setOnClickListener { showAttendanceSubjectFragment() }
        binding.bExit.setOnClickListener { exitProcess(1) }
        return binding.root
    }

    /* override fun onSubjectSelected(title: String?) {
         val action =
             StartFragmentDirections.actionSubjectListFragmentToSubjectDetailsFragment(title ?: "")
         findNavController().navigate(action)
     }*/

    private fun showCreateNewSubjectFragment() {
        val action = StartFragmentDirections.actionStartFragmentToNewSubjectFragment()
        findNavController().navigate(action)
    }

    private fun showListAttendanceFragment() {
        val action = StartFragmentDirections.actionStartFragmentToSubjectAttendanceFragment()
        findNavController().navigate(action)
    }

    private fun showListSubjectFragment() {
        val action = StartFragmentDirections.actionStartFragmentToSubjectListFragment()
        findNavController().navigate(action)
    }

    private fun showAttendanceSubjectFragment() {
        val action = StartFragmentDirections.actionStartFragmentToSubjectsListFragment()
        findNavController().navigate(action)
    }
}

