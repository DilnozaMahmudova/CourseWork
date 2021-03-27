package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class AllTaskContract {
    interface Model {
        fun getAllTask(): List<TaskData>
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
        fun getDeadline(): List<TaskData>
        fun getDone(): List<TaskData>
        fun getCancel(): List<TaskData>
    }

    interface View {
        fun loadData(
            task1: List<TaskData>,
            task2: List<TaskData>,
            task3: List<TaskData>,
            task4: List<TaskData>
        )
        fun openTask(taskData: TaskData)
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
    }


    interface Presenter {
        fun openTask(taskData: TaskData)
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
        fun loadData()
    }
}