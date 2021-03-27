package uz.dilnoza.coursework.ui.dialogs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.add_dialog.*
import kotlinx.android.synthetic.main.add_dialog.view.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.utils.extentions.SingleBlock
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*

class TaskDialog(context: Context, actionName: String) : AlertDialog(context) {
    private val contentView = LayoutInflater.from(context).inflate(R.layout.add_dialog, null, false)
    private var listener: SingleBlock<TaskData>? = null
    private var taskData: TaskData? = null
    private val formatter1= DateTimeFormatter.ofPattern("yyyy-MM-dd")
    private val formatter2= DateTimeFormatter.ofPattern("HH:mm")


    init {
        setView(contentView)
        val cal = Calendar.getInstance()
        var years = cal.get(Calendar.YEAR)
        var months = cal.get(Calendar.MONTH)
        var days = cal.get(Calendar.DAY_OF_MONTH)
        var hour = cal.get(Calendar.HOUR)
        var min = cal.get(Calendar.MINUTE)
        contentView.taskDate.setOnClickListener {
            val dialog = DatePickerDialog(
                context,
                DatePickerDialog.OnDateSetListener(function = { _, year, month, dayOfMonth ->
                    val dialogTime = TimePickerDialog(
                        context,
                        TimePickerDialog.OnTimeSetListener(function = { _, hourOfDay, minute ->
                            taskTime.text = "$hourOfDay:$minute"
                            hour = hourOfDay
                            min = minute
                            taskTime.text=LocalTime.of(hour,min).format(formatter2)
                        }),
                        0,
                        0,
                        true
                    )
                    dialogTime.updateTime(hour, min)
                    dialogTime.show()
                    taskDate.text = "$dayOfMonth/${month + 1}/$year "
                    years = year
                    months = month + 1
                    days = dayOfMonth
                    taskDate.text= LocalDate.of(years,months,days).format(formatter1)
                }),
                years,
                months,
                days
            )
            dialog.updateDate(years, months, days)
            dialog.show()

        cal.set(years, months, days, hour, min)
        }

        contentView.apply {
            ok.setOnClickListener {

                val data = taskData ?: TaskData(0, "", LocalDateTime.of(LocalDate.parse(contentView.taskDate.text),
                    LocalTime.parse(contentView.taskTime.text)), "", "", "")
                    data.name = contentView.taskName.text.toString()
                    data.hashtag = contentView.taskHashtag.text.toString()
                    data.info = contentView.taskInfo.text.toString()
                    data.dateTime = LocalDateTime.of(LocalDate.parse(contentView.taskDate.text),
                        LocalTime.parse(contentView.taskTime.text))
                when {
                    contentView.taskName.text.toString() == "" -> Toast.makeText(
                        context,
                        "Task name is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentView.taskHashtag.text.toString() == "" -> Toast.makeText(
                        context,
                        "Hash tag is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentView.taskInfo.text.toString() == "" -> Toast.makeText(
                        context,
                        "Information is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentView.taskDate.text == "Date" -> Toast.makeText(
                        context,
                        "Date is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    contentView.taskTime.text == "Time" -> Toast.makeText(
                        context,
                        "Date is empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        listener?.invoke(data)
                        dismiss()
                        Toast.makeText(context, "Successfully action", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            cancel.setOnClickListener { dismiss() }
        }
    }


    fun setTaskData(taskData: TaskData) = with(contentView) {
        this@TaskDialog.taskData = taskData
        taskName.setText(taskData.name)
        taskHashtag.setText(taskData.hashtag)
        taskInfo.setText(taskData.info)
        taskDate.text = taskData.dateTime.toLocalDate().toString()
        taskTime.text = taskData.dateTime.toLocalTime().toString()
    }

    fun setOnClickListener(block: SingleBlock<TaskData>) {
        listener = block
    }
}
