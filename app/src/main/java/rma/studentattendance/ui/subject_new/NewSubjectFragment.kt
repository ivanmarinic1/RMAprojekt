package rma.studentattendance.ui.subject_new

//import rma.studentattendance.di.SubjectRepositoryFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studentattendance.R
import rma.studentattendance.databinding.FragmentNewSubjectBinding
import rma.studentattendance.model.SubjectPlace
import rma.studentattendance.presentation.SubjectNewViewModel
import java.lang.Integer.parseInt

class NewSubjectFragment : Fragment() {

    //private val subjectRepository = SubjectRepositoryFactory.subjectRepository
    lateinit var binding: FragmentNewSubjectBinding
    private val viewModel: SubjectNewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewSubjectBinding.inflate(layoutInflater)
        binding.bSaveSubject.setOnClickListener { saveSubject() }

        return binding.root
    }

    private fun goBack() {
        val action = NewSubjectFragmentDirections.actionNewSubjectFragmentToStartFragment()
        findNavController().navigate(action)
    }

    private fun saveSubject() {

        val title = binding.etSubjectTitleInput.text.toString()
        val classes = binding.etSubjectClassInput.text.toString()


        val place = when (binding.rgPlaceInput.checkedRadioButtonId) {
            R.id.rb_kampus -> SubjectPlace.Kampus
            R.id.rb_trpimirova -> SubjectPlace.Trpimirova
            else -> SubjectPlace.Trpimirova
        }

        if (title == "" || classes == "" || binding.rgPlaceInput.getCheckedRadioButtonId() == -1) {
            Toast.makeText(context, "Prazan string", Toast.LENGTH_SHORT).show()
        } else {

            viewModel.save(title, parseInt(classes), place, 0, 0, "", 0.0, 0, "Null", 0)

            Toast.makeText(context, "Saving", Toast.LENGTH_SHORT).show()
            val action =
                NewSubjectFragmentDirections.actionNewSubjectFragmentToSubjectListFragment()
            findNavController().navigate(action)
        }
    }

    companion object {
        val Tag = "NewSubject"

        fun create(): Fragment {
            return NewSubjectFragment()
        }
    }
}