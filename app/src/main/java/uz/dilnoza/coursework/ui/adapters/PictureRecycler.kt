package uz.dilnoza.coursework.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.picture_page.view.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.utils.extentions.bindItem
import uz.dilnoza.coursework.utils.extentions.inflate

class PictureRecycler(
    private val ls: List<Int>
) :
    RecyclerView.Adapter<PictureRecycler.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder((parent.inflate(R.layout.picture_page)))

    override fun getItemCount() = ls.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bind() = bindItem {
            val d = ls[adapterPosition]
            image_directory.setImageResource(d)
        }
    }
}
