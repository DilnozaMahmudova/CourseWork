package uz.dilnoza.coursework.ui.adapters

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import kotlinx.android.synthetic.main.item_note.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.utils.extentions.SingleBlock
import uz.dilnoza.coursework.utils.extentions.bindItem
import uz.dilnoza.coursework.utils.extentions.inflate

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var sort: SortedList<TaskData>
    private var listenerItem: SingleBlock<TaskData>? = null
    private var listenerDone: SingleBlock<TaskData>? = null
    private var listenerCancel: SingleBlock<TaskData>? = null
    private val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun submitList(data: List<TaskData>) {
        sort.clear()
        sort.beginBatchedUpdates()
        for (item:TaskData in data){
            sort.addAll(item)
        }
        sort.endBatchedUpdates()
        notifyItemRangeRemoved(0,data.size)
    }

    fun removeItem(data: TaskData) {
        sort.remove(data)
    }

    fun setOnNextClickListener(block: SingleBlock<TaskData>) {
        listenerItem = block
    }

    fun setOnDoneClickListener(block: SingleBlock<TaskData>) {
        listenerDone = block
    }

    fun setOnCancelListener(block: SingleBlock<TaskData>) {
        listenerCancel = block
    }

    init {

        sort = SortedList(TaskData::class.java, object : SortedList.Callback<TaskData>() {
            override fun areItemsTheSame(item1: TaskData, item2: TaskData) = item1.id == item2.id

            override fun onMoved(fromPosition: Int, toPosition: Int) {
                notifyItemMoved(fromPosition, toPosition)
            }

            override fun onChanged(position: Int, count: Int) {
                notifyItemRangeChanged(position, count)
            }

            override fun onInserted(position: Int, count: Int) {
                notifyItemRangeInserted(position, count)
            }

            override fun onRemoved(position: Int, count: Int) {
                notifyItemRangeRemoved(position, count)
            }

            override fun compare(o1: TaskData, o2: TaskData) = o1.dateTime.compareTo(o2.dateTime)

            override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData) =
                oldItem.name == newItem.name && oldItem.hashtag == newItem.hashtag
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_note))

    override fun getItemCount(): Int = sort.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                next.setOnClickListener { listenerItem?.invoke(sort[adapterPosition]) }
                did.setOnClickListener { listenerDone?.invoke(sort[adapterPosition]) }
                didnt.setOnClickListener { listenerCancel?.invoke(sort[adapterPosition]) }
            }
        }

        fun bind() = bindItem {
            val d = sort[adapterPosition]
            title.text = d.name
            hashtag.text = d.hashtag
            deadline.text = d.dateTime.format(formatter)
                if (d.dateTime.dayOfYear - LocalDate.now().dayOfYear in 0..1) {color.setBackgroundColor(Color.parseColor("#8C1310"))
                next.setColorFilter(Color.parseColor("#8C1310"))}
                if (d.dateTime.dayOfYear - LocalDate.now().dayOfYear in 2..3) {color.setBackgroundColor(Color.parseColor("#E29402"))
                    next.setColorFilter(Color.parseColor("#E29402"))}
                if (3 < d.dateTime.dayOfYear - LocalDate.now().dayOfYear) {color.setBackgroundColor(Color.parseColor("#187F14"))
                    next.setColorFilter(Color.parseColor("#187F14"))}
        }
    }
}


