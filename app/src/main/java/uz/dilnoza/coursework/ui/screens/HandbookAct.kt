package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_all_task.listPicture
import kotlinx.android.synthetic.main.activity_all_task.tabLay
import kotlinx.android.synthetic.main.activity_full_item.home
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.ui.adapters.DirectoryAdapter
import uz.dilnoza.coursework.ui.transformers.Transformers

class HandbookAct : AppCompatActivity() {
    private lateinit var adapter: DirectoryAdapter
    private val transformers = Transformers()
    val list = listOf(
        "Main screen",
        "Add task",
        "All tasks",
        "Basket",
        "Edit",
        "History",
        "Share",
        "Rules"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handbook)
        adapter = DirectoryAdapter(this)
        listPicture.adapter = adapter
        listPicture.setPageTransformer(transformers)
        TabLayoutMediator(tabLay, listPicture) { tab, position ->
            tab.text = list[position]
        }.attach()
        home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}