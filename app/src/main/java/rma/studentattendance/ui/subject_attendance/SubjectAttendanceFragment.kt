package rma.studentattendance.ui.subject_attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rma.studentattendance.ui.subject_list.OnSubjectSelectedListener
import rma.studentattendance.databinding.FragmentSubjectListBinding
//import rma.studentattendance.di.SubjectRepositoryFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.FragmentSubjectAttendanceBinding
import rma.studentattendance.ui.subject_attendance.AttendanceAdapter

class SubjectAttendanceFragment : Fragment() {

    private lateinit var binding: FragmentSubjectAttendanceBinding
    private lateinit var adapter: AttendanceAdapter
    //private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    private val viewModel: SubjectListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectAttendanceBinding.inflate(layoutInflater)

        binding.fabBack.setOnClickListener{
            goBack()
        }
        setupRecyclerView()
        viewModel.subjects.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.setSubjects(it)
            }
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.subjectListRvSubjects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = AttendanceAdapter()
        binding.subjectListRvSubjects.adapter = adapter
    }

    /* override fun onResume() {
         super.onResume()
         updateData()
     }

     private fun updateData() {
         adapter.setSubjects(subjectRepository.getAllSubjects())
     }
 */
    companion object {
        val Tag = "SubjectsList"

        fun create(): Fragment {
            return SubjectAttendanceFragment()
        }
    }


    private fun goBack() {
        val action = SubjectAttendanceFragmentDirections.actionSubjectAttendanceFragmentToStartFragment()
        findNavController().navigate(action)
    }
}

