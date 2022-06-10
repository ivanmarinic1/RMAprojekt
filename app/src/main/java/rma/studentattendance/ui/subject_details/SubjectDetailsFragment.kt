package rma.studentattendance.ui.subject_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import rma.studentattendance.data.model.Subject
import rma.studentattendance.di.SubjectRepositoryFactory
import rma.studentattendance.databinding.FragmentSubjectDetailsBinding
import rma.studentattendance.getColorResource

class SubjectDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSubjectDetailsBinding
    private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    private val args: SubjectDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val subject = subjectRepository.getSubjectByTitle(args.subjectTitle)
        display(subject)
    }

    private fun display(subject: Subject?) {
        subject?.let {
            binding.apply {
                tvSubjectDetailsTitle.text = subject.title
                tvSubjectDetailsContents.text = subject.classes
            }
        }
    }

    companion object {
        val Tag = "TasksDetails"
        val TaskIdKey = "TaskId"

        fun create(id: Long): Fragment {
            val fragment = SubjectDetailsFragment()
            return fragment
        }
    }
}