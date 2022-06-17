package rma.studentattendance.presentation

import androidx.lifecycle.ViewModel
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.model.Subject
import rma.studentattendance.model.SubjectPlace
import java.util.*

class SubjectNewViewModel(
    val subjectRepository: SubjectRepository
) : ViewModel() {
    fun save(title: String,classes: Int, place: SubjectPlace, classesAttended: Int, classesBunked: Int, currentAttendance: String, percentageAttendance: Double, classesCanBeBunked:Int, status: String,classesMustAttend:Int ) {
        subjectRepository.save(Subject(0,title, classes, place, classesAttended, classesBunked, currentAttendance, percentageAttendance, classesCanBeBunked, status, classesMustAttend ))
    }
}