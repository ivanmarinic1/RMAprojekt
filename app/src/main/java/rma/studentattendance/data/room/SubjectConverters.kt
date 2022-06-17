package rma.studentattendance.data.room

import androidx.room.TypeConverter
import rma.studentattendance.data.model.Subject
import rma.studentattendance.model.SubjectPlace
import java.util.*

class SubjectConverters {



    @TypeConverter
    fun toString(place: SubjectPlace): String{
        return place.toString()
    }

    @TypeConverter
    fun fromString(place: String): SubjectPlace{
        return when(place){
            SubjectPlace.Kampus.toString() -> SubjectPlace.Kampus
            SubjectPlace.Trpimirova.toString() -> SubjectPlace.Trpimirova
            else -> SubjectPlace.Trpimirova
        }
    }
}