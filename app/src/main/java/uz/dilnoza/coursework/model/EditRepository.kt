package uz.dilnoza.coursework.model

import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.EditContract
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class EditRepository:EditContract.Model {
    private val database= AppDatabase.getDatabase(App.instance)
    private var taskDao=database.taskDao()
    override fun getTask(): List<TaskData> {
        return taskDao.getAll()
    }

    override fun updateTask(taskData: TaskData) {
        taskDao.update(taskData)
    }

    override fun cancelTask(taskData: TaskData) {
        taskDao.update(taskData)
    }

    override fun deleteTask(taskData: TaskData) {
        taskDao.update(taskData)
    }


}