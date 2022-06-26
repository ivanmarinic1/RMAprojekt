package rma.studentattendance.data

import androidx.lifecycle.LiveData
import rma.studentattendance.data.model.Subject


interface SubjectRepository {

    fun save(subject: Subject)
    fun delete(subject: Subject)
    fun getSubjectByTitle(title: String): Subject?
    fun getAllSubjects(): LiveData<List<Subject>>
    fun observeTotalClassesAttended(): LiveData<Int>
    fun observeTotalClassesBunked(): LiveData<Int>
    fun observeTotalMustAttendClasses(): LiveData<Int>
    fun observeTotalCanBunkClasses(): LiveData<Int>
    fun updateSubjectItem(subject: Subject)
}

