package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class MainContracts{
    interface Model{
        fun getTask():List<TaskData>
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
        fun deadline(taskData: TaskData)
    }

    interface View{
        fun loadData(task:List<TaskData>)
        fun openTask(taskData: TaskData)
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
    }

    interface Presenter{
        fun openTask(taskData: TaskData)
        fun cancel(taskData: TaskData)
        fun done(taskData: TaskData)
        fun sort()

    }
}
