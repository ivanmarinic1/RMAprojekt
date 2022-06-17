package rma.studentattendance.data

import androidx.lifecycle.LiveData
import rma.studentattendance.data.model.Subject
import rma.studentattendance.data.SubjectDao


class SubjectRepositoryImpl(val subjectDao: SubjectDao) : SubjectRepository {
    override fun save(subject: Subject) = subjectDao.save(subject)
    override fun delete(subject: Subject) = subjectDao.delete(subject)
    override fun getSubjectByTitle(title: String): Subject? = subjectDao.getSubjectByTitle(title)
    override fun getAllSubjects(): LiveData<List<Subject>> = subjectDao.getAllSubjects()
    override fun observeTotalClassesAttended(): LiveData<Int> = subjectDao.observeTotalClassesAttended()
    override fun observeTotalClassesBunked(): LiveData<Int> = subjectDao.observeTotalClassesBunked()
    override fun observeTotalMustAttendClasses(): LiveData<Int> = subjectDao.observeTotalMustAttendClasses()
    override fun observeTotalCanBunkClasses(): LiveData<Int> = subjectDao.observeTotalCanBunkClasses()
    override fun updateSubjectItem(subject: Subject) = subjectDao.updateSubjectItem(subject)

}