package rma.studentattendance

import android.content.res.Resources
import rma.studentattendance.R
import rma.studentattendance.model.SubjectPlace

fun Resources.getPictureResource(place: SubjectPlace): Int{
    return when(place){
        SubjectPlace.Kampus -> R.drawable.kampus
        SubjectPlace.Trpimirova -> R.drawable.trpimirova
    }
}
fun Resources.getUrlResource(place: SubjectPlace): String{
    return when(place){
        SubjectPlace.Kampus -> "https://www.google.com/maps/place/Fakultet+elektrotehnike,+ra%C4%8Dunarstva+i+informacijskih+tehnologija+Osijek+(FERIT+Osijek)+-+Cara+Hadrijana+10b/@45.5565765,18.709687,17z/data=!3m1!4b1!4m5!3m4!1s0x475ce7c492ca69d5:0x4b900191a071a06f!8m2!3d45.5565765!4d18.7118757"
        SubjectPlace.Trpimirova -> "https://www.google.com/maps/place/Fakultet+elektrotehnike,+ra%C4%8Dunarstva+i+informacijskih+tehnologija+Osijek+(FERIT+Osijek)+-+Kneza+Trpimira+2B/@45.5566938,18.6935038,17z/data=!3m1!4b1!4m5!3m4!1s0x475ce7b9c3d3213d:0x43b5fc47f7e90641!8m2!3d45.5566938!4d18.6956925"
    }
}

fun Resources.getLanResource(place: SubjectPlace): Double  {
    return when (place) {
        SubjectPlace.Kampus ->  45.5565823
        SubjectPlace.Trpimirova -> 45.5566874
    }
}
fun Resources.getLongResource(place: SubjectPlace): Double  {
    return when (place) {
        SubjectPlace.Kampus ->  18.7118871
        SubjectPlace.Trpimirova -> 18.6956948
    }
}

