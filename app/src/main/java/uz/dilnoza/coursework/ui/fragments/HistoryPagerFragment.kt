package uz.dilnoza.coursework.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.dilnoza.coursework.R
import uz.dilnoza.coursework.contract.HistoryContract
import uz.dilnoza.coursework.model.HistoryRepository
import uz.dilnoza.coursework.presenter.HistoryPresenter
import uz.dilnoza.coursework.room.entity.TaskData
import uz.dilnoza.coursework.ui.adapters.HistoryAdapter
import uz.dilnoza.coursework.ui.screens.FullItem


class HistoryPagerFragment(private val position:Int):Fragment(R.layout.pager),HistoryContract.View{
  lateinit var list:RecyclerView
    lateinit var presenter:HistoryPresenter
      private  val adapter=HistoryAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list=view.findViewById(R.id.listEdit)
        presenter= HistoryPresenter(HistoryRepository(),this)
        list.adapter=adapter
        adapter.setOnItemClickListener(presenter::openTask)
        val ln=LinearLayoutManager(activity)
        list.layoutManager=ln
    }

    override fun loadData(
        taskDone: List<TaskData>,
        taskDead: List<TaskData>,
        taskCancel: List<TaskData>
    ) {
        when(position){
            0->adapter.submitList(taskDone)
            1->adapter.submitList(taskDead)
            2->adapter.submitList(taskCancel)
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
}