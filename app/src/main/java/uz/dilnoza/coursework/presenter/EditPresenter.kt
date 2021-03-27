package uz.dilnoza.coursework.presenter

import android.os.Handler
import android.os.Looper
import uz.dilnoza.coursework.contract.EditContract
import uz.dilnoza.coursework.room.entity.TaskData
import java.util.concurrent.Executors

class EditPresenter(private val model:EditContract.Model, private val view:EditContract.View):EditContract.Presenter{
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())
   init {
       runOnWorkerThread {
       val ls=model.getTask()
      runOnUIThread { view.loadData(ls) }
       }
   }
    override fun openTask(taskData: TaskData) {
        view.openTask(taskData)
    }

    override fun deleteTask(taskData: TaskData) {
        taskData.status="Delete"
      runOnWorkerThread {
       model.deleteTask(taskData)
       runOnUIThread { view.deleteTask(taskData) }
      }
    }

    override fun confirmEditTask(taskData: TaskData) {
        runOnWorkerThread {
         model.updateTask(taskData)
            runOnUIThread {view.updateTask(taskData) }
        }
    }

    override fun editTask(taskData: TaskData) {
        taskData.status=""
      view.openEditDialog(taskData)
    }

    override fun cancelTask(taskData: TaskData) {
        taskData.status="Cancel"
       runOnWorkerThread {
        model.cancelTask(taskData)
       runOnUIThread { view.cancelTask(taskData) }
       }
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }

}