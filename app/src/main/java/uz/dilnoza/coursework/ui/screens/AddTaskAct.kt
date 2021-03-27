package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_task.*
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.AddContract
import uz.dilnoza.coursework.model.AddRepository
import uz.dilnoza.coursework.presenter.AddPresenter
import uz.dilnoza.coursework.ui.dialogs.TaskDialog

class AddTaskAct : AppCompatActivity(), AddContract.View {
    private lateinit var presenter: AddContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        title = "Add Task"
        presenter = AddPresenter(AddRepository(), this)
        add.setOnClickListener { presenter.openAddTask() }
        home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun openDialog() {
        val dialog = TaskDialog(this, "Add")
        dialog.setOnClickListener(presenter::creatTask)
        dialog.show()

    }
}