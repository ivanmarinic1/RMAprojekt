package rma.studentattendance.ui.subject_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.ItemSubjectBinding
import rma.studentattendance.getPictureResource

class SubjectAdapter : RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    val subjects = mutableListOf<Subject>()
    var onSubjectSelectedListener: OnSubjectSelectedListener? = null

    fun setSubjects(subjects: List<Subject>) {
        this.subjects.clear()
        this.subjects.addAll(subjects)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        val subject = subjects[position]
        holder.bind(subject)
        onSubjectSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onSubjectSelected(subject.title) }
            holder.itemView.setOnLongClickListener { listener.onSubjectLongPress(subject) }
        }
    }

    override fun getItemCount(): Int = subjects.count()


inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(subject: Subject) {
        val binding = ItemSubjectBinding.bind(itemView)
        binding.itemSubjectTitle.text = subject.title
        binding.itemSubjectClasses.text = subject.classes.toString()
        binding.itemSubjectPlacee.text = subject.place.toString()
        binding.itemSubjectPlace.setBackgroundResource(
            binding.itemSubjectPlace.context.resources.getPictureResource(subject.place)
        )
    }
}
}

