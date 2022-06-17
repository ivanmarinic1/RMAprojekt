package rma.studentattendance.presentation

import androidx.lifecycle.ViewModel
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.model.Subject

class SubjectDetailsViewModel(
    val taskRepository: SubjectRepository
) : ViewModel() {

    fun getSubjectByTitle(title: String?): Subject? {
        var subject: Subject? = null
        title?.let { subject = taskRepository.getSubjectByTitle(title) }
        return subject
    }
}