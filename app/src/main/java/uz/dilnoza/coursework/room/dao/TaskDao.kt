package uz.dilnoza.coursework.room.dao

import androidx.room.Dao
import androidx.room.Query
import uz.dilnoza.coursework.room.entity.TaskData
@Dao
interface TaskDao:BaseDao<TaskData> {
    @Query("Select * From TaskData")
    fun getAll():List<TaskData>
    @Query("Select * from TaskData where status=='Done'")
    fun getDoneList():List<TaskData>
    @Query("Select * from TaskData where status=='Cancel'")
    fun getCancelList():List<TaskData>
    @Query("Select * from TaskData where status=='Delete'")
    fun getDeleteList():List<TaskData>
    @Query("Select * from TaskData where status==''")
    fun getList():List<TaskData>
    @Query("Select * from TaskData where status=='Deadline'")
    fun getDeadlineList():List<TaskData>
}