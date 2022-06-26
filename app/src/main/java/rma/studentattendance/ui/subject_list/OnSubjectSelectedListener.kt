package rma.studentattendance.ui.subject_list

import rma.studentattendance.data.model.Subject

interface OnSubjectSelectedListener {
    fun onSubjectSelected(title: String?)
    fun onSubjectLongPress(subject: Subject?): Boolean
}