import android.widget.TextView
import androidx.databinding.BindingAdapter
import rma.studentattendance.R
import rma.studentattendance.data.model.Subject

//todo add binding adapters to the subjectItems
@BindingAdapter("subjectPercentageAttendance")
fun TextView.setAttendancPercentage(item: Subject){
    text = "${item.percentageAttendance}%"
}
@BindingAdapter("subjectName")
fun TextView.setSubjectTitle(item:Subject){
    text = item.title
}
@BindingAdapter("currentAttendance")
fun TextView.setCurrentAttendance(item: Subject){
    text = item.currentAttendance
    setTextColor(resources.getColor(R.color.purple_500))
}
