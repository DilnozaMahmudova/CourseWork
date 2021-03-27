package uz.dilnoza.coursework.presenter

import android.os.Handler
import android.os.Looper
import uz.dilnoza.coursework.contract.BasketContract
import uz.dilnoza.coursework.room.entity.TaskData
import java.util.concurrent.Executors

class BasketPresenter(
    private val model: BasketContract.Model,
    private val view: BasketContract.View
) : BasketContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())

    init {
        runOnWorkerThread {
            val ls = model.getDeletedTask()
            runOnUIThread { view.loadData(ls) }
        }
    }

    override fun openTask(taskData: TaskData) {
        view.openTask(taskData)
    }

    override fun restore(taskData: TaskData) {
        taskData.status=""
        runOnWorkerThread {
            model.restore(taskData)
            runOnUIThread { view.restore(taskData) }
        }
    }

    override fun delete(taskData: TaskData) {
        taskData.status="Delete"
        runOnWorkerThread {
            model.delete(taskData)
            runOnUIThread { view.delete(taskData) }
        }
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}