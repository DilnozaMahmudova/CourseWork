package uz.dilnoza.coursework.model

import uz.dilnoza.coursework.app.App
import uz.dilnoza.coursework.contract.HistoryContract
import uz.dilnoza.coursework.room.AppDatabase
import uz.dilnoza.coursework.room.entity.TaskData

class HistoryRepository : HistoryContract.Model {
    private val database = AppDatabase.getDatabase(App.instance)
    private val taskDao=database.taskDao()
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