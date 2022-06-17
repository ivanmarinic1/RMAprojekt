package rma.studentattendance.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rma.studentattendance.model.SubjectPlace
import java.util.*

@Entity(tableName = "subjects")
data class Subject(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "title") // Optional!
    var title: String ?= null,
    @ColumnInfo(name = "classes")
    val classes: Int,
    @ColumnInfo(name = "place")
    val place: SubjectPlace,
    @ColumnInfo(name = "classes attended")
    var classesAttended:Int,
    @ColumnInfo(name = "classes bunked")
    var classesBunked:Int,
    @ColumnInfo(name = "current attendance")
    var currentAttendance: String,
    @ColumnInfo(name = "percentage attendance")
    var percentageAttendance:Double = if(classes == 0)0.0 else Math.round((classesAttended.toDouble()*100/classes).toDouble() * 10.0)/10.0,
    @ColumnInfo(name = "classes can be bunked")
    var classesCanBeBunked:Int?=null,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "classes must attend")
    var classesMustAttend:Int?=null,

    ) {

}