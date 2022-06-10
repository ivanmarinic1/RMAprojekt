package rma.studentattendance.data

import rma.studentattendance.data.model.Subject


interface SubjectDao {

    fun save(subject: Subject)
    fun delete(subject: Subject)
    fun getSubjectByTitle(title: String): Subject?
    fun getAllSubjects(): List<Subject>
}

