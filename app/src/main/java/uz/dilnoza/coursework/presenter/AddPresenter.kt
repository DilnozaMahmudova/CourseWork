package uz.dilnoza.coursework.presenter

import uz.dilnoza.coursework.contract.AddContract
import uz.dilnoza.coursework.room.entity.TaskData
import java.util.concurrent.Executors

class AddPresenter(private val model: AddContract.Model, private val view: AddContract.View) :
    AddContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    
    override fun openAddTask() { view.openDialog() }

    override fun creatTask(taskData: TaskData) { runOnWorkerThread { model.insertTask(taskData) } }

    private fun runOnWorkerThread(f: () -> Unit) { executor.execute { f() } }
    
}