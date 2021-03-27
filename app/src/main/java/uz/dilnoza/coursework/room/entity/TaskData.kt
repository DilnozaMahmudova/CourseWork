package uz.dilnoza.coursework.room.entity

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var dateTime: org.threeten.bp.LocalDateTime,
    var hashtag: String,
    var info: String,
    var status: String
){
    companion object{
        val ITEM_CALLBACK=object :DiffUtil.ItemCallback<TaskData>(){
            override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData)=oldItem.id==newItem.id

            override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData)=oldItem.name==newItem.name && oldItem.info==newItem.info

        }
    }
}