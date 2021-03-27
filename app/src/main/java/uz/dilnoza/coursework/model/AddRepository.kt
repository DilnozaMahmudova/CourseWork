package uz.dilnoza.coursework.model

import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.AddContract
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class AddRepository:AddContract.Model {
    private val database=AppDatabase.getDatabase(App.instance)
    private val taskDao=database.taskDao()
    override fun insertTask(taskData: TaskData) {
        val id=taskDao.insert(taskData)
        taskData.id=id
    }
}