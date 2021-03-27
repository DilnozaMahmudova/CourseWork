package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class HistoryContract {
    interface Model {
        fun getDeadline(): List<TaskData>
        fun getDone(): List<TaskData>
        fun getCancel(): List<TaskData>
    }

    interface View {
        fun loadData(taskDone:List<TaskData>, taskDead:List<TaskData>, taskCancel:List<TaskData>)
        fun openTask(taskData: TaskData)
    }
    interface Presenter{
        fun openTask(taskData: TaskData)
    }
}
