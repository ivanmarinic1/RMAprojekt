package rma.studentattendance.ui.subject_list

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.R
import rma.studentattendance.data.SubjectDao
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.ItemSubjectsBinding
import rma.studentattendance.presentation.SubjectNewViewModel
import rma.studentattendance.di.viewmodelModule
import rma.studentattendance.model.SubjectPlace
import rma.studentattendance.ui.MainActivity
import rma.studentattendance.ui.subject_new.NewSubjectFragmentDirections


class SubjectsAdapter : RecyclerView.Adapter<SubjectsViewHolder>() {
        val subjects = mutableListOf<Subject>()
        var onSubjectSelectedListener: OnSubjectSelectedListener? = null

        fun setSubjects(subjects: List<Subject>) {
            this.subjects.clear()
            this.subjects.addAll(subjects)
            this.notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_subjects, parent, false)
            return SubjectsViewHolder(view)
        }

        override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
            val subject = subjects[position]
            holder.bind(subject)
            onSubjectSelectedListener?.let { listener ->
                holder.itemView.setOnClickListener {
                    listener.onSubjectSelected(subject.title)

                }
                holder.itemView.setOnLongClickListener{ listener.onSubjectLongPress(subject) }
            }
        }

    override fun getItemCount(): Int = subjects.count()
}

class SubjectsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemSubjectsBinding.bind(itemView)
    /*fun bind(subject: Subject) {
        val binding = ItemSubjectsBinding.bind(itemView)
        binding.tvSubject.text = subject.title.toString()
        binding.tvClasses.text = subject.classes.toString()
        binding.tvCurrentAttendance.text = "Null"
        binding.tvStatus.text = "Null"
    }*/

        fun bind(item: Subject){
            item.percentageAttendance = if(item.classes == 0) 0.0 else Math.round((item.classesAttended.toDouble()*100/item.classes).toDouble() * 10.0)/10.0
            binding.executePendingBindings()
            binding.tvSubject.text = item.title.toString()
            binding.tvClasses.text = item.classes.toString()
            binding.tvCurrentAttendance.text = "Null"
            binding.tvStatus.text = "Null"

            fun changeStatus() {
                binding.tvCurrentAttendance.text = "Current Attendance : ${item.classesAttended}/${item.classes}"
                if(item.percentageAttendance.toInt() < 75){
                    binding.tvStatus.text = "Bili ste na ${item.classesAttended}, a niste bili na ${item.classesBunked} ."
                    binding.tvStatus.setTextColor(Color.RED)
                }
                else {
                    binding.tvStatus.text = "Bili ste na ${item.classesAttended}, a niste bili na ${item.classesBunked} ."
                    binding.tvStatus.setTextColor(Color.GREEN)
                }
            }
            changeStatus()

            binding.buBunk.setOnClickListener {
                if (item.classesAttended + item.classesBunked == item.classes)
                    item.currentAttendance = "Predavanja su gotova"
                else{
                item.classesBunked++

                item.currentAttendance = "Current Attendance ${item.classesAttended}/${item.classes}"
                var percentage = item.classesAttended.toDouble() * 100.0/item.classes
                item.percentageAttendance = Math.round(percentage * 10.0)/10.0
                binding.tvCurrentAttendance.text = "Current Attendance : ${item.classesAttended}/${item.classes}"
                binding.tvPercent.text = "${item.percentageAttendance}%"
                changeStatus()
                }
            }

            binding.buAttend.setOnClickListener {
                if (item.classesAttended + item.classesBunked == item.classes)
                    item.currentAttendance = "Predavanja su gotova"
                else {
                    item.classesAttended++
                    item.currentAttendance =
                        "Current Attendance : ${item.classesAttended}/${item.classes}"
                    var percentage = item.classesAttended.toDouble() * 100.0 / item.classes
                    item.percentageAttendance = Math.round(percentage * 10.0) / 10.0
                    binding.tvCurrentAttendance.text = item.currentAttendance
                    binding.tvPercent.text = "${item.percentageAttendance}%"
                    changeStatus()
                }
            }

            val subject = Subject(
                title = item.title,
                classes = item.classes,
                place = item.place,
                classesAttended = item.classesAttended,
                classesBunked = item.classesBunked,
                currentAttendance = item.currentAttendance,
                percentageAttendance = item.percentageAttendance,
                classesCanBeBunked = item.classesCanBeBunked,
                status = item.status,
                classesMustAttend = item.classesMustAttend
            )
        }

    }


