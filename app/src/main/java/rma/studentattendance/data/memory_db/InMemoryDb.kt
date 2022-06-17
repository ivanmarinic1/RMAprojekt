/*package rma.studentattendance.data.memory_db

import androidx.lifecycle.LiveData
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.model.Subject
import rma.studentattendance.model.SubjectPlace
import java.util.*
import kotlin.random.Random

class InMemoryDb : SubjectDao {

    private val subjects = mutableListOf<Subject>()

    init {
        val places =
            arrayOf(SubjectPlace.Kampus, SubjectPlace.Trpimirova)
        for (i in 1..3) {
            save(
                Subject(
                    i.toLong(),
                    "Title$i",
                    2,
                    places[Random.nextInt(2)],
                    0,
                    0,
                    "",
                    0.0,
                    0,
                    "Null"
                )
            )
        }
    }

    override fun save(subject: Subject) {
        subjects.add(subject)
    }

    override fun delete(subject: Subject) {
        subjects.remove(subject)
    }

    override fun getSubjectByTitle(title: String): Subject? {
        return subjects.firstOrNull { it.title == title }
    }

    override fun getAllSubjects(): LiveData<List<Subject>> {

    }

    override fun observeTotalClassesAttended(): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override fun observeTotalClassesBunked(): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override fun observeTotalCanBunkClasses(): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override fun observeTotalMustAttendClasses(): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override fun updateSubjectItem(subject: Subject) {
        subjects.add(subject)
    }


}*/