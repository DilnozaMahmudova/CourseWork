package uz.dilnoza.coursework.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.directory_page.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.ui.adapters.PictureRecycler
import uz.dilnoza.coursework.ui.transformers.Horizontal


class DirectoryFragment(private val position: Int) : Fragment(R.layout.directory_page) {
    lateinit var list: RecyclerView
    private val title =
        listOf(
            "Main screen",
            "Add task",
            "All tasks",
            "Basket",
            "Edit",
            "History",
            "Share",
            "Rules"
        )
    private val info = listOf(
        "1.The tasks are presented in 3 different views in sequence according to the completion date \n 2.Switching from the main screen to the rest of the screens is easy",
        "When the \"Add\" button is clicked, a dialog is displayed to fill in the required properties of the task",
        "In this window the tasks are displayed in 4 different views. 'Canceled', 'Delay', 'Done' and 'Has time'. Tasks in the 'Has time' section can be canceled or added to the list of completed ones",
        "Deleted tasks are stored in the \"Basket\" section. Here they can be completely destroyed or restored",
        "In the \"Edit\" section, you can delete, cancel, and edit tasks",
        "In the \"History\" section, you can see that the tasks are divided into 3 parts according to their status",
        "When click the \"Share\" button, you can share the app with your loved ones through the following",
        "In the \"Rules\" section you can see the conveniences and features of the application"
    )
    private val main = listOf(R.drawable.main1, R.drawable.main2)
    private val addPicture = listOf(R.drawable.add1,R.drawable.add4, R.drawable.add2, R.drawable.add3)
    private val allPicture =
        listOf(R.drawable.all1, R.drawable.all2, R.drawable.all3, R.drawable.all4)
    private val basket = listOf(R.drawable.basket)
    private val edit = listOf(R.drawable.edital)
    private val history = listOf(R.drawable.history1, R.drawable.history2, R.drawable.history3)
    private val share = listOf(R.drawable.share1, R.drawable.share2)
    private val rules = listOf(
        R.drawable.into1,
        R.drawable.into2,
        R.drawable.into3,
        R.drawable.into4,
        R.drawable.into5
    )
    private lateinit var adapter: PictureRecycler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = view.findViewById(R.id.listRec)
        when (position) {
            0 -> adapter = PictureRecycler(main)
            1 -> adapter = PictureRecycler(addPicture)
            2 -> adapter = PictureRecycler(allPicture)
            3 -> adapter = PictureRecycler(basket)
            4 -> adapter = PictureRecycler(edit)
            5 -> adapter = PictureRecycler(history)
            6 -> adapter = PictureRecycler(share)
            7 -> adapter = PictureRecycler(rules)
        }

        title_template.text=title[position]
        information.text=info[position]
        list.adapter = adapter
        list.layoutManager=Horizontal(context,LinearLayoutManager.HORIZONTAL,false)


    }
}