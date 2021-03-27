package uz.dilnoza.coursework.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.dilnoza.coursework.ui.fragments.IntoFragment
import uz.dilnoza.coursework.utils.extentions.putArgument

class IntoPagerAdapter(private val info: List<String>, private val image:List<Int>,activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    private var listenerNext:((Int)->Unit)?=null
    private var listenerBack:((Int)->Unit)?=null
    override fun getItemCount() = info.size

    override fun createFragment(position: Int): Fragment = IntoFragment().apply {
        listenerBack?.let { this.setBack(it)}
        listenerNext?.let { this.setNext(it) }
    }.putArgument {
        putString("TEXT", info[position])
        putInt("IMAGE", image[position])
    }
    fun setNext(block:(Int)->Unit){
        listenerNext=block
    }
    fun setBack(block:(Int)->Unit){
        listenerBack=block
    }

}