package uz.dilnoza.coursework.presenter

import android.os.Handler
import android.os.Looper
import uz.dilnoza.coursework.contract.HistoryContract
import uz.dilnoza.coursework.room.entity.TaskData
import java.util.concurrent.Executors

class HistoryPresenter(
    private val model: HistoryContract.Model,
    private val view: HistoryContract.View
) : HistoryContract.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())

    init {
        runOnWorkerThread {
            val ls1 = model.getDone()
            val ls2 = model.getDeadline()
            val ls3 = model.getCancel()
            runOnUIThread { view.loadData(ls1, ls2, ls3) }
        }
    }

    override fun openTask(taskData: TaskData) {
        view.openTask(taskData)
    }

    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }

}