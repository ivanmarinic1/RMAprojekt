package rma.studentattendance

import android.content.res.Resources
import rma.studentattendance.R
import rma.studentattendance.data.model.SubjectPlace

fun Resources.getColorResource(place: SubjectPlace): Int{
    return when(place){
        SubjectPlace.Kampus -> R.color.purple_200
        SubjectPlace.Trpimirova -> R.color.purple_500
    }
}

