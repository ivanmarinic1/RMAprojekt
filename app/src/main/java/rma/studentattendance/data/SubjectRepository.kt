package rma.studentattendance.data

import rma.studentattendance.data.model.Subject


interface SubjectRepository {

    fun save(subject: Subject)
    fun delete(subject: Subject)
    fun getSubjectByTitle(title: String): Subject?
    fun getAllSubjects(): List<Subject>
}

