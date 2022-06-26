package rma.studentattendance.ui.subject_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.FragmentSubjectsListBinding
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver

//import rma.studentattendance.di.SubjectRepositoryFactory

class SubjectsListFragment : Fragment(), OnButtonSelectedListener {

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
        adapter.onButtonSelectedListener = this

        binding.subjectsListRvSubjects.adapter = adapter
    }

    companion object {
        val Tag = "SubjectsList"

        fun create(): Fragment {
            return SubjectListFragment()
        }
    }

    private fun goBack() {
        val action = SubjectsListFragmentDirections.actionSubjectsListFragmentToStartFragment()
        findNavController().navigate(action)
    }

    override fun onAttendSelected( subject: Subject) {
            subject?.let { it ->
                viewModel.updateSubjectItem(subject)
            }
    }

    override fun onBunkSelected(subject: Subject) {
            subject?.let { it ->
                viewModel.updateSubjectItem(subject)
            }
    }

}
