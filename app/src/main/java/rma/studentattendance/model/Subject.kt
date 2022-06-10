package rma.studentattendance.data.model

sealed class SubjectPlace{
    object Kampus : SubjectPlace()
    object Trpimirova : SubjectPlace()
}

data class Subject(
    val title: String,
    val classes: Int,
    val place: SubjectPlace
) {

}