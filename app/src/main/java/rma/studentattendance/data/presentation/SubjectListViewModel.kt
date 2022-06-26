package rma.studenattendance.presentation

import androidx.lifecycle.ViewModel
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.model.Subject

class SubjectListViewModel(
    val subjectRepository: SubjectRepository
) : ViewModel() {
    val subjects = subjectRepository.getAllSubjects()

    fun delete(subject: Subject) {
        subjectRepository.delete(subject)
    }

    fun updateSubjectItem(subject: Subject) {
        subjectRepository.updateSubjectItem(subject)
    }


}
