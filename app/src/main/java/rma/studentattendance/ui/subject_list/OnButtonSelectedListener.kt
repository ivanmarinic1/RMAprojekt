package rma.studentattendance.ui.subject_list

import rma.studentattendance.data.model.Subject

interface OnButtonSelectedListener {
    fun onAttendSelected(subject: Subject)
    fun onBunkSelected(subject: Subject)
}