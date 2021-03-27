package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.EditContract
import uz.dilnoza.coursework.model.EditRepository
import uz.dilnoza.coursework.presenter.EditPresenter
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.ui.adapters.EditActAdapter
import uz.dilnoza.coursework.ui.dialogs.TaskDialog

class EditAct : AppCompatActivity(), EditContract.View {
    private lateinit var presenter: EditContract.Presenter
    private val adapter = EditActAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        title = "Edit tasks"
        presenter = EditPresenter(EditRepository(), this)
        listEdit.layoutManager = LinearLayoutManager(this)
        listEdit.adapter = adapter
        adapter.setOnEditListener(presenter::editTask)
        adapter.setOnItemClickListener(presenter::openTask)
        adapter.setOnItemDeleteListener(presenter::deleteTask)
        adapter.setOnItemCancelListener(presenter::cancelTask)
        home.setOnClickListener {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        }
    }

    override fun loadData(data: List<TaskData>) {
        adapter.submitList(data)
    }

    override fun openEditDialog(taskData: TaskData) {
        val dialog = TaskDialog(this, "Edit")
        dialog.setTaskData(taskData)
        dialog.setOnClickListener(presenter::confirmEditTask)
        dialog.show()

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

    override fun updateTask(taskData: TaskData) {
        val ls = adapter.currentList.toMutableList()
        val index = ls.indexOfFirst { it.id == taskData.id }
        ls[index] = taskData
        adapter.submitList(ls)
        adapter.notifyItemChanged(index)
    }

    override fun deleteTask(taskData: TaskData) {
        val ls = adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }

    override fun cancelTask(taskData: TaskData) {
        val ls = adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }
}