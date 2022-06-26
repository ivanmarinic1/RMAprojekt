package rma.studentattendance.ui.subject_list

import android.graphics.Color
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studenattendance.presentation.SubjectListViewModel
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject
import rma.studentattendance.databinding.ItemSubjectsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import rma.studentattendance.data.SubjectRepository
import rma.studentattendance.di.viewmodelModule



class SubjectsAdapter : RecyclerView.Adapter<SubjectsAdapter.SubjectsViewHolder>() {
    val subjects = mutableListOf<Subject>()
    var onButtonSelectedListener: OnButtonSelectedListener? = null

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


        onButtonSelectedListener?.let { listener ->
            holder.binding.buAttend.setOnClickListener {
                holder.bind(subject)

            }
            holder.binding.buBunk.setOnClickListener { listener.onBunkSelected(subject) }
                holder.bind(subject)
        }
    }

    override fun getItemCount(): Int = subjects.count()

inner class SubjectsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ItemSubjectsBinding.bind(itemView)
    fun bind(item: Subject) {

        item.percentageAttendance =
            if (item.classes == 0) 0.0 else Math.round((item.classesAttended.toDouble() * 100 / item.classes).toDouble() * 10.0) / 10.0
        binding.executePendingBindings()
        binding.tvSubject.text = "Predmet: " + item.title.toString()
        binding.tvClasses.text = "Sati predavanja: " + item.classes.toString()
        if(item.percentageAttendance.toInt() == 0)
        binding.tvCurrentAttendance.text ="Trenutna prisutnost: " + item.currentAttendance.toString()
        else
            binding.tvCurrentAttendance.text = item.currentAttendance
        binding.tvStatus.text = item.status
        binding.tvPercent.text = item.percentageAttendance.toString() + "%"
        if(item.percentageAttendance < 75)
            binding.tvPercent.setTextColor(Color.RED)
        else if(item.percentageAttendance >= 75)
            binding.tvPercent.setTextColor(Color.GREEN)


        fun changeStatus() {
            //binding.tvCurrentAttendance.text =
              //  "Trenutna prisutnost: ${item.classesAttended}/${item.classes},  "
            if(item.classesAttended == 0 && item.classesBunked == 0){
                binding.tvStatus.text =
                    "Predavanja nisu počela. "
                item.status = binding.tvStatus.text.toString()
                binding.tvStatus.setTextColor(Color.YELLOW)
                }
            else if (item.percentageAttendance.toInt() < 75 && (item.classesAttended + item.classesBunked) < item.classes && !item.classesMustAttend.toString().startsWith("-") && !item.classesCanBeBunked
                    .toString().startsWith("-") && item.percentageAttendance.toInt() !=75) {
                binding.tvStatus.text =
                    "Da bi ostvarili 75% morate biti na još        ${item.classesMustAttend} sati, a možete izostati sa ${item.classesCanBeBunked} "
                item.status = binding.tvStatus.text.toString()
                binding.tvStatus.setTextColor(Color.RED)

            } else if ((item.percentageAttendance.toInt() >= 75 && (item.classesAttended + item.classesBunked) < item.classes) &&( item.classesMustAttend == 0 || item.classesMustAttend.toString().startsWith("-"))) {
                binding.tvStatus.text =
                    "Ostvarili ste 75% dolaznosti, možete izostati ostatak predavanja. "
                item.status = binding.tvStatus.text.toString()
                binding.tvStatus.setTextColor(Color.GREEN)
            } else if((item.classesAttended + item.classesBunked)== item.classes){
                item.status = "Predavanja su gotova"
                binding.tvStatus.text = item.status
                item.percentageAttendance = item.classesAttended.toDouble() * 100.0 / item.classes
            }
            else if(item.classesMustAttend.toString().startsWith("-")){
                binding.tvStatus.text = "Ne morate više ići na predavanja"
            }
            else if(item.classesCanBeBunked.toString().startsWith("-")){
                binding.tvStatus.text = "Više nemate mogućnost ostvariti 75%. "
            }

        }
        changeStatus()

        binding.buBunk.setOnClickListener {
            if(item.classesAttended + item.classesBunked == item.classes){
                changeStatus()
            }else {
                item.classesBunked++
                item.classesMustAttend =
                    ((0.75 * item.classes.toDouble()) - item.classesAttended).toInt()
                item.classesCanBeBunked =
                    ((0.25 * item.classes.toDouble()) - item.classesBunked).toInt()
                item.currentAttendance =
                    "Trenutna prisutnost:  ${item.classesAttended}/${item.classes},  "
                var percentage = item.classesAttended.toDouble() * 100.0 / item.classes
                item.percentageAttendance = Math.round(percentage * 10.0) / 10.0
                binding.tvCurrentAttendance.text =
                    "Trenutna prisutnost:  ${item.classesAttended}/${item.classes},  "
                binding.tvPercent.text = "${item.percentageAttendance}%"
                changeStatus()
                onButtonSelectedListener?.onBunkSelected(item)
            }
            }

        binding.buAttend.setOnClickListener {
            if (item.classesAttended + item.classesBunked == item.classes) {
                changeStatus()
            } else {
                item.classesAttended++
                item.classesMustAttend =
                    ((0.75 * item.classes.toDouble()) - item.classesAttended).toInt()
                item.classesCanBeBunked =
                    ((0.25 * item.classes.toDouble()) - item.classesBunked).toInt()
                item.currentAttendance =
                    "Trenutna prisutnost:  ${item.classesAttended}/${item.classes},  "
                var percentage = item.classesAttended.toDouble() * 100.0 / item.classes
                item.percentageAttendance = Math.round(percentage * 10.0) / 10.0
                binding.tvCurrentAttendance.text = item.currentAttendance
                binding.tvCurrentAttendance.text =
                    "Trenutna prisutnost:  ${item.classesAttended}/${item.classes},  "
                binding.tvPercent.text = "${item.percentageAttendance} %"
                changeStatus()
                onButtonSelectedListener?.onAttendSelected(item)
            }
        }
        }
    }
}







