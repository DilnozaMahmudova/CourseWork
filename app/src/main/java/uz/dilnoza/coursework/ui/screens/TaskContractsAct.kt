package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_task_contracts.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.ui.adapters.IntoPagerAdapter
import uz.dilnoza.coursework.ui.transformers.CubeRotate

class TaskContractsAct : AppCompatActivity() {
    private lateinit var adapter: IntoPagerAdapter
    private val image =
        arrayListOf(R.drawable.p3, R.drawable.p5, R.drawable.p1, R.drawable.p2, R.drawable.p4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_contracts)
        val info = arrayListOf(
            "Add to the note the work that needs to be done.",
            "From the Home screen, separate tasks into completed or canceled ones",
            "There is no problem with the deadline",
            "It is easy and fast to find the desired function through hash tags",
            "Tasks are grouped according to status"
        )
        adapter= IntoPagerAdapter(info,image,this)
        listPicture.adapter=adapter
        listPicture.setPageTransformer(CubeRotate())
        adapter.setBack { listPicture.currentItem = listPicture.currentItem - 1 }
        adapter.setNext {  if (listPicture.currentItem != info.size - 1) {
            listPicture.setCurrentItem(listPicture.currentItem + 1, true)
        } else {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }}
        TabLayoutMediator(tabLay, listPicture) { tab, position ->

        }.attach()
        tabLay.selectedTabPosition
    }
}