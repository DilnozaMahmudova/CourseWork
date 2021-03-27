package uz.dilnoza.coursework.presenter


import android.os.Handler
import android.os.Looper
import uz.dilnoza.coursework.contract.MainContracts
import uz.dilnoza.coursework.room.entity.TaskData
import org.threeten.bp.LocalDateTime
import java.util.concurrent.Executors


class MainPresenter(
    private val model: MainContracts.Model,
    private val view: MainContracts.View
) : MainContracts.Presenter {
    private var executor = Executors.newSingleThreadExecutor()
    private var handle = Handler(Looper.getMainLooper())

    init {
        runOnWorkerThread {
            for (task: TaskData in model.getTask()) {
                if (task.dateTime.isBefore(LocalDateTime.now())) {
                    task.status="Deadline"
                    model.deadline(task)
                }
            }
        }
        runOnWorkerThread {
            val ls = model.getTask()
            runOnUIThread {
                view.loadData(ls)
            }
        }
    }


    override fun openTask(taskData: TaskData) {
        view.openTask(taskData)


    }

    override fun cancel(taskData: TaskData) {
        taskData.status = "Cancel"
        runOnWorkerThread {
            model.cancel(taskData)
            runOnUIThread { view.cancel(taskData) }
        }
    }

    override fun done(taskData: TaskData) {
        taskData.status = "Done"
        runOnWorkerThread {
            model.done(taskData)
            runOnUIThread { view.done(taskData) }
        }
    }


    override fun sort() {

    }


    private fun runOnWorkerThread(f: () -> Unit) {
        executor.execute { f() }
    }

    private fun runOnUIThread(f: () -> Unit) {
        handle.post { f() }
    }
}