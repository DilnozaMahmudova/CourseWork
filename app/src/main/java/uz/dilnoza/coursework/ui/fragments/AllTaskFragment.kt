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
import uz.dilnoza.coursework.ui.adapters.AllTaskAdapter
import uz.dilnoza.coursework.ui.screens.FullItem

class AllTaskFragment(private val position:Int): Fragment(R.layout.pager),AllTaskContract.View {
    lateinit var list: RecyclerView
    lateinit var presenter:AllTaskPresenter
    val adapter= AllTaskAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter= AllTaskPresenter(AllTaskRepository(),this)
        list=view.findViewById(R.id.listEdit)
        list.adapter=adapter

        adapter.setOnItemClickListener{presenter.openTask(it)}
        val ln= LinearLayoutManager(activity)
        list.layoutManager=ln
    }

    override fun loadData(
        task1: List<TaskData>,
        task2: List<TaskData>,
        task3: List<TaskData>,
        task4: List<TaskData>
    ) {
        when(position){
            0->{
                adapter.submitList(task1)
            }
            1->{
                adapter.submitList(task2)
            }
            2->{
                adapter.submitList(task3)

            }

        }

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
        val ls=adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }

    override fun done(taskData: TaskData) {
        val ls=adapter.currentList.toMutableList()
        ls.remove(taskData)
        adapter.submitList(ls)
    }
}