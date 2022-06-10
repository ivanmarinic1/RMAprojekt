package rma.studentattendance.di

import rma.studentattendance.data.memory_db.InMemoryDb
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.SubjectRepositoryImpl

object SubjectRepositoryFactory {

    private val subjectDao: SubjectDao = InMemoryDb()
    val subjectRepository: SubjectRepository = SubjectRepositoryImpl(subjectDao)
}