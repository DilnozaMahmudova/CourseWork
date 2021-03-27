package uz.dilnoza.coursework.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.into_page.*
import uz.dilnoza.coursework.R

class IntoFragment:Fragment(R.layout.into_page) {

    private var listenerNext: ((Int)->Unit)?=null
    private var listenerBack: ((Int) -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle=requireArguments()
        val image=bundle.getInt("IMAGE")
        info.text=bundle.getString("TEXT")
        page.setBackgroundResource(image)
        back.setOnClickListener {
            listenerBack?.invoke(image)
        }
        next.setOnClickListener {
            listenerNext?.invoke(image)
        }
    }
    fun setNext(block: (Int) -> Unit) {
        listenerNext = block
    }

    fun setBack(block: (Int) -> Unit) {
        listenerBack = block
    }
}