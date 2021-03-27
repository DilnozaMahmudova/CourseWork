package uz.dilnoza.coursework.model

import androidx.lifecycle.LiveData
import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.AllTaskContract
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class AllTaskRepository :AllTaskContract.Model{
    private val database = AppDatabase.getDatabase(App.instance)
    private var taskDao=database.taskDao()
    override fun getAllTask(): List<TaskData> {
     return   taskDao.getList()
    }

    override fun cancel(taskData: TaskData) {
        taskDao.update(taskData)

    }

    override fun done(taskData: TaskData) {
        taskDao.update(taskData)

    }

    override fun getDeadline(): List<TaskData> {
        return taskDao.getDeadlineList()
    }

    override fun getDone(): List<TaskData> {
        return taskDao.getDoneList()
    }

    override fun getCancel(): List<TaskData> {
        return taskDao.getCancelList()
    }
}