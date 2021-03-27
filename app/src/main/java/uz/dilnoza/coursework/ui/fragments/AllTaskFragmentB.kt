package uz.dilnoza.coursework.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.AllTaskContract
import uz.dilnoza.coursework.model.AllTaskRepository
import uz.dilnoza.coursework.presenter.AllTaskPresenter
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.ui.adapters.AllTaskAdapterB
import uz.dilnoza.coursework.ui.screens.FullItem


class AllTaskFragmentB : Fragment(R.layout.pager), AllTaskContract.View {
    lateinit var list: RecyclerView
    val adapter = AllTaskAdapterB()
    lateinit var presenter: AllTaskPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = view.findViewById(R.id.listEdit)
        presenter = AllTaskPresenter(AllTaskRepository(), this)
        list.adapter = adapter
        adapter.setOnItemClickListener(presenter::openTask)
        adapter.setOnCancelListener(presenter::cancel)
        adapter.setOnDoneClickListener(presenter::done)
        val ln = LinearLayoutManager(activity)
        list.layoutManager = ln
    }

    override fun loadData(
        task1: List<TaskData>,
        task2: List<TaskData>,
        task3: List<TaskData>,
        task4: List<TaskData>
    ) {
        adapter.submitList(task4)

    }

    override fun openTask(taskData: TaskData) {
        val intent = Intent(context, FullItem::class.java)
        intent.putExtra("TITLE", taskData.name)
        intent.putExtra("HASHTAG", taskData.hashtag)
        intent.putExtra("INFO", taskData.info)
        intent.putExtra("DATETIME", taskData.dateTime.toString())
        startActivity(intent)
    }

    override fun cancel(taskData: TaskData) {
        val ls = adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)


    }

    override fun done(taskData: TaskData) {
        val ls = adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }

}
