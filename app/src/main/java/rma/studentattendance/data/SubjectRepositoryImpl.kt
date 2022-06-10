package rma.studentattendance.data

import rma.studentattendance.data.model.Subject


class SubjectRepositoryImpl(val subjectDao: SubjectDao) : SubjectRepository {
    override fun save(subject: Subject) = subjectDao.save(subject)
    override fun delete(subject: Subject) = subjectDao.delete(subject)
    override fun getSubjectByTitle(title: String): Subject? = subjectDao.getSubjectByTitle(title)
    override fun getAllSubjects(): List<Subject> = subjectDao.getAllSubjects()
}