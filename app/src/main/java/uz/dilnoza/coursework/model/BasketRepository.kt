package uz.dilnoza.coursework.model

import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.BasketContract
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class BasketRepository :BasketContract.Model{
    private val database= AppDatabase.getDatabase(App.instance)
    private val taskDao=database.taskDao()
    override fun getDeletedTask(): List<TaskData> {
        return taskDao.getDeleteList()
    }

    override fun restore(taskData: TaskData) {
        taskDao.update(taskData)
    }

    override fun delete(taskData: TaskData) {
      taskDao.delete(taskData)
    }
}