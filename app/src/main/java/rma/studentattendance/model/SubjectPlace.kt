package rma.studentattendance.model

sealed class SubjectPlace {
    object Kampus : SubjectPlace() {
        override fun toString(): String = "Kampus"
    }

    object Trpimirova : SubjectPlace() {
        override fun toString(): String = "Trpimirova"
    }
}