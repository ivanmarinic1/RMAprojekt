package rma.studentattendance.ui.subject_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject
import rma.studentattendance.data.model.SubjectPlace
import rma.studentattendance.databinding.FragmentNewSubjectBinding
import rma.studentattendance.di.SubjectRepositoryFactory
import java.lang.Integer.parseInt

class NewSubjectFragment : Fragment() {

    private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    lateinit var binding: FragmentNewSubjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewSubjectBinding.inflate(layoutInflater)
        binding.bSaveSubject.setOnClickListener{saveSubject()}
        return binding.root
    }

    private fun saveSubject() {

        val title = binding.etSubjectTitleInput.text.toString()
        val classes = binding.etSubjectClassInput.text.toString()


        val place = when(binding.rgPlaceInput.checkedRadioButtonId){
            R.id.rb_kampus -> SubjectPlace.Kampus
            R.id.rb_trpimirova -> SubjectPlace.Trpimirova
        }

        subjectRepository.save(Subject(title, parseInt(classes), place))

        Toast.makeText(context, "Saving", Toast.LENGTH_SHORT).show()
        val action = NewSubjectFragmentDirections.actionNewSubjectFragmentToSubjectListFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "NewSubject"

        fun create(): Fragment {
            return NewSubjectFragment()
        }
    }
}