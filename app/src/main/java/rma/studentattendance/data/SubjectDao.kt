package rma.studentattendance.data

import androidx.lifecycle.LiveData
import androidx.room.*
import rma.studentattendance.data.model.Subject


@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(subject: Subject)

    @Delete
    fun delete(subject: Subject)

    @Update
    fun updateSubjectItem(subject: Subject)

    @Query("SELECT * FROM subjects WHERE title =:title")
    fun getSubjectByTitle(title: String): Subject?

    @Query("SELECT * FROM subjects")
    fun getAllSubjects(): LiveData<List<Subject>>

    @Query("select SUM(`classes must attend`) from subjects")
    fun observeTotalMustAttendClasses():LiveData<Int>

    @Query("select SUM(`classes can be bunked`) from subjects")
    fun observeTotalCanBunkClasses():LiveData<Int>

    @Query("select SUM(`classes attended`) from subjects")
    fun observeTotalClassesAttended(): LiveData<Int>

    @Query("select SUM(`classes` - `classes attended`) from subjects")
    fun observeTotalClassesBunked(): LiveData<Int>
}

