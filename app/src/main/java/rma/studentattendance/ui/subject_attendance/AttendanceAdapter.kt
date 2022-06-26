package rma.studentattendance.ui.subject_attendance

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.ItemAttendanceBinding
import rma.studentattendance.getPictureResource
import rma.studentattendance.ui.subject_list.OnSubjectSelectedListener

class AttendanceAdapter : RecyclerView.Adapter<AttendanceViewHolder>() {
    val subjects = mutableListOf<Subject>()
    var onSubjectSelectedListener: OnSubjectSelectedListener? = null

    fun setSubjects(subjects: List<Subject>) {
        this.subjects.clear()
        this.subjects.addAll(subjects)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance, parent, false)
        return AttendanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val subject = subjects[position]
        holder.bind(subject)
        onSubjectSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onSubjectSelected(subject.title) }
            holder.itemView.setOnLongClickListener { listener.onSubjectLongPress(subject) }
        }
    }

    override fun getItemCount(): Int = subjects.count()
}

class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(subject: Subject) {
        val binding = ItemAttendanceBinding.bind(itemView)
        binding.itemSubjectTitle.text = subject.title
        binding.itemSubjectClasses.text = subject.classes.toString()
        binding.itemSubjectPlacee.text = subject.place.toString()
        binding.itemSubjectPlace.setBackgroundResource(
            binding.itemSubjectPlace.context.resources.getPictureResource(subject.place)
        )

        binding.progressBar.setProgress(subject.percentageAttendance.toInt())
        binding.tvProgress.text = subject.percentageAttendance.toInt().toString() + "%"
        if(subject.percentageAttendance.toInt() < 75)
            binding.progressBar.progressDrawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
        else if(subject.percentageAttendance.toInt() >= 75)
            binding.progressBar.progressDrawable.setColorFilter(Color.GREEN,PorterDuff.Mode.SRC_IN)
        if(subject.classesAttended + subject.classesBunked < subject.classes && (subject.classesAttended + subject.classesBunked != 0))
        binding.tvProgressSubject.text = "U tijeku"
        else if(subject.classesAttended + subject.classesBunked == subject.classes)
            binding.tvProgressSubject.text = "Gotovo"
        else if((subject.classesAttended == 0) && (subject.classesBunked == 0))
            binding.tvProgressSubject.text = "Nije počelo"
    }
}

