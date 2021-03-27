package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_all_task.*
import kotlinx.android.synthetic.main.activity_all_task.home
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.ui.adapters.HistoryPagerAdapter
import uz.dilnoza.coursework.ui.transformers.Transformers

class HistoryAct : AppCompatActivity() {
    private val transformers = Transformers()
    val list = listOf("Done", "Delay", "Canceled")
    private lateinit var adapter: HistoryPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_task)
        title = "History of tasks"
        adapter = HistoryPagerAdapter(this)
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