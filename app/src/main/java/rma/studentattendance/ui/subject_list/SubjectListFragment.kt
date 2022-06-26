package rma.studentattendance.ui.subject_list

//import rma.studentattendance.di.SubjectRepositoryFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.FragmentSubjectListBinding

class SubjectListFragment : Fragment(), OnSubjectSelectedListener {

    private lateinit var binding: FragmentSubjectListBinding
    private lateinit var adapter: SubjectAdapter

    //private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    private val viewModel: SubjectListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubjectListBinding.inflate(layoutInflater)

        binding.fabAddSubject.setOnClickListener { showCreateNewSubjectFragment() }

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
        adapter = SubjectAdapter()
        adapter.onSubjectSelectedListener = this
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
            return SubjectListFragment()
        }
    }

    override fun onSubjectSelected(title: String?) {
        val action =
            SubjectListFragmentDirections.actionSubjectListFragmentToSubjectDetailsFragment(
                title ?: ""
            )
        findNavController().navigate(action)
    }

    override fun onSubjectLongPress(subject: Subject?): Boolean {
        subject?.let { it ->
            viewModel.delete(it)

        }
        return true
    }

    private fun showCreateNewSubjectFragment() {
        val action = SubjectListFragmentDirections.actionSubjectListFragmentToNewSubjectFragment()
        findNavController().navigate(action)
    }

    private fun goBack() {
        val action = SubjectListFragmentDirections.actionSubjectListFragmentToStartFragment()
        findNavController().navigate(action)
    }
}

