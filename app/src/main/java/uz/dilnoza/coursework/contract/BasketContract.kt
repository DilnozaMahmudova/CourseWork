package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class BasketContract{
    interface Model{
        fun getDeletedTask():List<TaskData>
        fun restore(taskData: TaskData)
        fun delete(taskData: TaskData)
    }

    interface View{
        fun loadData(data:List<TaskData>)
        fun restore(taskData: TaskData)
        fun delete(taskData: TaskData)
        fun openTask(taskData: TaskData)
    }
    interface Presenter{
        fun openTask(taskData: TaskData)
        fun restore(taskData: TaskData)
        fun delete(taskData: TaskData)
    }
}