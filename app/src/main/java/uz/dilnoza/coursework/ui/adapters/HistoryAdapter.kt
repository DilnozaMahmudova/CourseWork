package uz.dilnoza.coursework.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history.view.*
import org.threeten.bp.format.DateTimeFormatter
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.utils.extentions.SingleBlock
import uz.dilnoza.coursework.utils.extentions.bindItem
import uz.dilnoza.coursework.utils.extentions.inflate

class HistoryAdapter: ListAdapter<TaskData,HistoryAdapter.ViewHolder>(TaskData.ITEM_CALLBACK) {
    private var listenerItem:SingleBlock<TaskData>?=null
    private val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=ViewHolder((parent.inflate(
        R.layout.item_history)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind()


    fun setOnItemClickListener(block: SingleBlock<TaskData>){
        listenerItem=block
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(getItem(adapterPosition)) }
            }
        }
        fun bind()=bindItem {
            val d=getItem(adapterPosition)
            title.text=d.name
            hashtag.text=d.hashtag
            deadline.text=d.dateTime.format(formatter)
        }

    }
}
