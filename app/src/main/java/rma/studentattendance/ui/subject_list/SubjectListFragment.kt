package rma.studentattendance.ui.subject_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rma.studentattendance.ui.subject_list.OnSubjectSelectedListener
import rma.studentattendance.databinding.FragmentSubjectListBinding
import rma.studentattendance.di.SubjectRepositoryFactory

class SubjectListFragment : Fragment(), OnSubjectSelectedListener {

    private lateinit var binding: FragmentSubjectListBinding
    private lateinit var adapter: SubjectAdapter
    private val subjectRepository = SubjectRepositoryFactory.subjectRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectListBinding.inflate(layoutInflater)
        setupRecyclerView()
        binding.fabAddNote.setOnClickListener { showCreateNewSubjectFragment() }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.subjectListRvSubjects.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = SubjectAdapter()
        adapter.onSubjectSelectedListener = this
        binding.subjectListRvSubjects.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        adapter.setSubjects(subjectRepository.getAllSubjects())
    }

    companion object {
        val Tag = "SubjectsList"

        fun create(): Fragment {
            return SubjectListFragment()
        }
    }

    override fun onSubjectSelected(id: Long?) {
        val action =
            SubjectListFragmentDirections.actionSubjectListFragmentToSubjectDetailsFragment(id ?: -1)
        findNavController().navigate(action)
    }

    private fun showCreateNewSubjectFragment() {
        val action = SubjectListFragmentDirections.actionSubjectListFragmentToNewSubjectFragment()
        findNavController().navigate(action)
    }
}

