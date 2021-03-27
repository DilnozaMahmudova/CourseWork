package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.BasketContract
import uz.dilnoza.coursework.model.BasketRepository
import uz.dilnoza.coursework.presenter.BasketPresenter
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.ui.adapters.BasketAdapter

class BasketAct : AppCompatActivity(),BasketContract.View {
    private lateinit var presenter:BasketContract.Presenter
    private val adapter=BasketAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title="Basket"
        presenter=BasketPresenter(BasketRepository(),this)
        listEdit.layoutManager=LinearLayoutManager(this)
            listEdit.adapter=adapter
        adapter.setOnItemClickListener (presenter::openTask)
        adapter.setOnItemDeleteListener (presenter::delete)
        adapter.setOnItemInsertListener (presenter::restore)
        home.setOnClickListener { startActivity(Intent(this, MainActivity::class.java))
            finish() }
    }

    override fun loadData(data: List<TaskData>) {
        adapter.submitList(data)
    }

    override fun restore(taskData: TaskData) {
       val ls= adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }

    override fun delete(taskData: TaskData) {
        val ls= adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
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

}