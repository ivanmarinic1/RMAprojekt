/*package rma.studentattendance.di
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.data.SubjectRepositoryImpl
import rma.studentattendance.data.model.Subject
import rma.studentattendance.data.room.SubjectDatabase
import rma.studentattendance.StudentAttendance

object SubjectRepositoryFactory {

    val roomDb = SubjectDatabase.getDatabase(StudentAttendance.application)
    val subjectRepository: SubjectRepository = SubjectRepositoryImpl(roomDb.getSubjectDao())
}*/