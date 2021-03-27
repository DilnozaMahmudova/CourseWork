package uz.dilnoza.coursework.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_full_item.*
import uz.dilnoza.coursework.R

class FullItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_item)
        title="Task"
        taskName.text = intent.getStringExtra("TITLE")
        hashTag.text = intent.getStringExtra("HASHTAG")
        taskInfo.text = intent.getStringExtra("INFO")
        deadline.text = intent.getStringExtra("DATETIME")
        home.setOnClickListener { startActivity(Intent(this, MainActivity::class.java))
            finish() }
    }

}