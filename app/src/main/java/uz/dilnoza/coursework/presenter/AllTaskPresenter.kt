package uz.dilnoza.coursework.presenter

import android.os.Handler
import android.os.Looper
import uz.dilnoza.coursework.contract.AllTaskContract
import uz.dilnoza.coursework.room.entity.TaskData
import java.util.concurrent.Executors

class AllTaskPresenter(val model:AllTaskContract.Model, val view:AllTaskContract.View):AllTaskContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
    init {
       loadData()
    }
    override fun openTask(taskData: TaskData) {
        view.openTask(taskData)
    }

    override fun cancel(taskData: TaskData) {
        taskData.status="Cancel"
        runOnWorkerThread {
            model.cancel(taskData)
            runOnUIThread { view.cancel(taskData) }
        }
    }

    override fun done(taskData: TaskData) {
        taskData.status="Done"
       runOnWorkerThread {
        model.done(taskData)
       runOnUIThread { view.done(taskData) }
       }
    }

    override fun loadData(){
        runOnWorkerThread {
            val ls1=model.getCancel()
            val ls2=model.getDeadline()
            val ls3=model.getDone()
            val ls4= model.getAllTask()
            runOnUIThread { view.loadData(ls1,ls2,ls3,ls4) }
        }
    }


    private fun runOnWorkerThread(f: () -> Unit) { executor.execute { f() } }

    private fun runOnUIThread(f: () -> Unit) { handle.post { f() } }
}