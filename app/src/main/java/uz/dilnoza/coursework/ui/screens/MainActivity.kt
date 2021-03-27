package uz.dilnoza.coursework.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.MainContracts
import uz.dilnoza.coursework.model.MainRepository
import uz.dilnoza.coursework.presenter.MainPresenter
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.ui.adapters.MainAdapter

class MainActivity : AppCompatActivity(), MainContracts.View {
    private lateinit var presenter: MainContracts.Presenter
    private lateinit var adapter: MainAdapter
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        sharedPref = this.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        if (sharedPref.getBoolean("SPLASH", true)) {
            editor.putBoolean("SPLASH", false).apply()
            val homeIntent = Intent(this, TaskContractsAct::class.java)
            startActivity(homeIntent)
        }
        setContentView(R.layout.activity_main)
        title = "MainScreen"
        presenter = MainPresenter(MainRepository(), this)
        adapter = MainAdapter()
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnCancelListener(presenter::cancel)
        adapter.setOnDoneClickListener(presenter::done)
        adapter.setOnNextClickListener {
            openTask(it)
        }

        menu.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        navigationView.setNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.all_task -> {
                    startActivity(Intent(this, AllTaskAct::class.java))
                    finish()
                }
                R.id.add_task -> {
                    startActivity(Intent(this, AddTaskAct::class.java))
                    finish()
                }
                R.id.basket -> {
                    startActivity(Intent(this, BasketAct::class.java))
                    finish()
                }
                R.id.history -> {
                    startActivity(Intent(this, HistoryAct::class.java))
                    finish()
                }
                R.id.edit -> {
                    startActivity(Intent(this, EditAct::class.java))
                    finish()
                }
                R.id.rules -> {
                    startActivity(Intent(this, TaskContractsAct::class.java))
                    finish()
                }
                R.id.instruction -> {
                    startActivity(Intent(this, HandbookAct::class.java))
                    finish()
                }
                R.id.share -> {
                    startActivity(Intent(this, ShareAct::class.java))
                    finish()
                }
            }
            item.isChecked = true
            drawerLayout.closeDrawers()
            true

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun loadData(task: List<TaskData>) {
        adapter.submitList(task)
    }

    override fun openTask(taskData: TaskData) {
        val intent = Intent(this, FullItem::class.java)
        intent.putExtra("TITLE", taskData.name)
        intent.putExtra("HASHTAG", taskData.hashtag)
        intent.putExtra("INFO", taskData.info)
        intent.putExtra("DATETIME", taskData.dateTime.toString())
        startActivity(intent)
        finish()
    }

    override fun cancel(taskData: TaskData) {
        adapter.removeItem(taskData)
    }

    override fun done(taskData: TaskData) {
        adapter.removeItem(taskData)
    }
}