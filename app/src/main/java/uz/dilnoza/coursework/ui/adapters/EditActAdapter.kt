package uz.dilnoza.coursework.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_edit.view.*
import org.threeten.bp.format.DateTimeFormatter
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.utils.extentions.SingleBlock
import uz.dilnoza.coursework.utils.extentions.bindItem
import uz.dilnoza.coursework.utils.extentions.inflate

class EditActAdapter: ListAdapter<TaskData,EditActAdapter.ViewHolder>(TaskData.ITEM_CALLBACK) {
    private var listenerItem:SingleBlock<TaskData>?=null
    private var listenerDelete:SingleBlock<TaskData>?=null
    private var listenerCancel:SingleBlock<TaskData>?=null
    private var listenerEdit:SingleBlock<TaskData>?=null
    private val formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder=ViewHolder((parent.inflate(
        R.layout.item_edit)))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)=holder.bind()

    fun setOnItemClickListener(block: SingleBlock<TaskData>){
        listenerItem=block
    }
    fun setOnItemCancelListener(block: SingleBlock<TaskData>){
        listenerCancel=block
    }
    fun setOnItemDeleteListener(block: SingleBlock<TaskData>){
        listenerDelete=block
    }
    fun setOnEditListener(block: SingleBlock<TaskData>){
        listenerEdit=block
    }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            itemView.apply {
                setOnClickListener { listenerItem?.invoke(getItem(adapterPosition)) }
                menuMore.setOnClickListener { view1 ->
                    val menu= PopupMenu(context,view1)
                    menu.inflate(R.menu.menu_edit_act)
                    menu.setOnMenuItemClickListener {
                        when(it.itemId){
                            R.id.menuCancel->listenerCancel?.invoke(getItem(adapterPosition))
                            R.id.menuDelete->listenerDelete?.invoke(getItem(adapterPosition))
                            R.id.menuEdit->listenerEdit?.invoke(getItem(adapterPosition))
                        }
                        true
                    }
                    menu.show()
                }
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
