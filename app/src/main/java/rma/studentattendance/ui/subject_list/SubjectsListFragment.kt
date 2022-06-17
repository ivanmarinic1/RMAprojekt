package rma.studentattendance.ui.subject_list

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject
import rma.studentattendance.ui.subject_list.OnSubjectSelectedListener
import rma.studentattendance.databinding.FragmentSubjectsListBinding
import rma.studentattendance.databinding.ItemSubjectsBinding
import rma.studentattendance.model.SubjectPlace
import rma.studentattendance.presentation.SubjectNewViewModel
import rma.studentattendance.ui.subject_new.NewSubjectFragmentDirections

//import rma.studentattendance.di.SubjectRepositoryFactory

class SubjectsListFragment : Fragment(), OnSubjectSelectedListener {

    private lateinit var binding: FragmentSubjectsListBinding
    private lateinit var adapter: SubjectsAdapter
    //private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    private val viewModel: SubjectListViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectsListBinding.inflate(layoutInflater)

        binding.fabAddNote.setOnClickListener{  }
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
        binding.subjectsListRvSubjects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = SubjectsAdapter()
        adapter.onSubjectSelectedListener = this
        binding.subjectsListRvSubjects.adapter = adapter
    }
/*
    override fun onResume() {
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
            return SubjectListFragment()
        }
    }

    override fun onSubjectSelected(title: String?) {
        val action = SubjectsListFragmentDirections.actionSubjectsListFragmentToSubjectDetailsFragment(title ?: "")
        findNavController().navigate(action)
    }

    override fun onSubjectLongPress(subject: Subject?): Boolean {
        subject?.let { it ->
            viewModel.updateSubjectItem(subject)
        }
        return true
    }

    private fun goBack() {
        val action = SubjectsListFragmentDirections.actionSubjectsListFragmentToStartFragment()
        findNavController().navigate(action)
    }

}
