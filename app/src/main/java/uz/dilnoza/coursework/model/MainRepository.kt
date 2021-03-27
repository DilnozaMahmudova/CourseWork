package uz.dilnoza.coursework.model

import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.MainContracts
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class MainRepository:MainContracts.Model {
    private val database=AppDatabase.getDatabase(App.instance)
    private var taskDao=database.taskDao()


    override fun getTask(): List<TaskData> {
        return taskDao.getList()
    }

    override fun cancel(taskData: TaskData){
        taskDao.update(taskData)
    }

    override fun done(taskData: TaskData) {
        taskDao.update(taskData)
    }

    override fun deadline(taskData: TaskData) {
        taskDao.update(taskData)
    }
}