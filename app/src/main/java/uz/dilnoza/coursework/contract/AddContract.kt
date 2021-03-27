package uz.dilnoza.coursework.contract

import uz.dilnoza.coursework.room.entity.TaskData

class AddContract {
    interface Model{
        fun insertTask(taskData: TaskData)
    }

    interface View{
        fun openDialog()
    }

    interface Presenter{
        fun openAddTask()
        fun creatTask(taskData: TaskData)
    }
}