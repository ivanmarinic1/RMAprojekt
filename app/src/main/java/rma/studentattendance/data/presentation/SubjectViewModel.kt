/*package rma.studentattendance.data.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import rma.studentattendance.data.model.Subject
import rma.studentattendance.other.Event
import rma.studentattendance.other.Resource
import kotlinx.coroutines.launch
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.model.SubjectPlace

class SubjectViewModel @ViewModelInject constructor(
    private val repository: SubjectRepository
) : ViewModel(){
    val subjectItems = repository.getAllSubjects()
    val totalMustAttend = repository.observeTotalMustAttendClasses()
    val totalCanBunk = repository.observeTotalCanBunkClasses()
    val totalClassesAttended = repository.observeTotalClassesAttended()
    val totalClassesBunked = repository.observeTotalClassesBunked()
    private val _insertSubjectItemStatus = MutableLiveData<Event<Resource<Subject>>>()

    fun insertSubjectItem(subject: Subject) = viewModelScope.launch {
        repository.save(subject)
    }

    fun getCompleteSubjectItem(
        subjectName: String,
        subjectPlace: SubjectPlace,
        requiredPercentage: String,
        classesAttended: String,
        totalClasses: String
    ): Subject {
        var status: String
        var currentAttendance : String
        var percentageAttendance : Double = if(totalClasses == "0") 0.0 else Math.round((classesAttended.toInt().toDouble()*100/totalClasses.toInt()).toDouble() * 10.0)/10.0
        var noOfClassesToAttend = 0
        var noOfClassesCanBeBunked = 0
        var requiredPercentage = requiredPercentage.toInt()
        if(totalClasses=="0"){
            status = "Attendance Not Yet Started"
            currentAttendance = "Attendance Not Yet Started"
        }
        else{
            currentAttendance = "Current Attendance : $classesAttended/$totalClasses"
            if(percentageAttendance<requiredPercentage){
                noOfClassesToAttend = 3 * totalClasses.toInt() - 4 * classesAttended.toInt()
                if (noOfClassesToAttend < 0) noOfClassesToAttend++
                var classesMustAttend = noOfClassesToAttend
                status = "To get More Than ${requiredPercentage}% Attend $noOfClassesToAttend classes"
            } else {
                var a = classesAttended.toInt()
                var t = totalClasses.toInt()
                while(a*100/t >= requiredPercentage.toDouble()){
                    noOfClassesCanBeBunked++
                    t++
                }
                if(a*100/t<75)noOfClassesCanBeBunked--
                status = if(noOfClassesCanBeBunked>0) "You Bunk $noOfClassesCanBeBunked classes" else "You Can't Bunk any Class Now"
            }
        }
        return Subject(
            title = subjectName,
            classes = totalClasses.toInt(),
            place = subjectPlace,
            classesAttended = classesAttended.toInt(),
            classesBunked = noOfClassesCanBeBunked,
            currentAttendance = currentAttendance,
            percentageAttendance = percentageAttendance,
            classesCanBeBunked = noOfClassesCanBeBunked,
            status = status,
            classesMustAttend = noOfClassesToAttend,
        )

    }

    fun updateSubjectItem(subject: Subject) = viewModelScope.launch{
        repository.updateSubjectItem(subject)
    }

}*/