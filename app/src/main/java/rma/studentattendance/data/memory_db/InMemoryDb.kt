package rma.studentattendance.data.memory_db

import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.model.Subject
import rma.studentattendance.data.model.SubjectPlace
import java.util.*
import kotlin.random.Random

class InMemoryDb : SubjectDao {

    private val subjects = mutableListOf<Subject>()

    init {
        val places =
            arrayOf(SubjectPlace.Kampus, SubjectPlace.Trpimirova)
        for (i in 1..30) {
            save(
                Subject(
                    "Title",
                    1,
                    places[Random.nextInt(3)])
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

    override fun getAllSubjects(): List<Subject> {
        return subjects
    }


}