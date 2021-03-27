package uz.dilnoza.coursework.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.dilnoza.coursework.ui.fragments.HistoryPagerFragment

class HistoryPagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity) {
    private lateinit var fragment:Fragment

    override fun getItemCount(): Int=3

    override fun createFragment(position: Int):Fragment{
       if (position==0){
           fragment=HistoryPagerFragment(position)
       }
        if(position==1){
            fragment=HistoryPagerFragment(position)
        }
        if (position==2){
            fragment=HistoryPagerFragment(position)
        }
        return fragment
    }
}