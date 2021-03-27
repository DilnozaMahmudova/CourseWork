package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class EditContract {
    interface Model{
        fun getTask():List<TaskData>
        fun updateTask(taskData: TaskData)
        fun cancelTask(taskData: TaskData)
        fun deleteTask(taskData: TaskData)

    }
    interface View{
        fun loadData(data:List<TaskData>)
        fun openEditDialog(taskData: TaskData)
        fun openTask(taskData: TaskData)
        fun updateTask(taskData: TaskData)
        fun deleteTask(taskData: TaskData)
        fun cancelTask(taskData: TaskData)


    }
    interface Presenter{
        fun openTask(taskData: TaskData)
        fun deleteTask(taskData: TaskData)
        fun confirmEditTask(taskData: TaskData)
        fun editTask(taskData: TaskData)
        fun cancelTask(taskData: TaskData)
    }
}