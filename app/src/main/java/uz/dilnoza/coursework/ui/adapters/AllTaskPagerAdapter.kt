package uz.dilnoza.coursework.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.dilnoza.coursework.ui.fragments.AllTaskFragment
import uz.dilnoza.coursework.ui.fragments.AllTaskFragmentB

class AllTaskPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {
    private lateinit var fragment: Fragment
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            fragment = AllTaskFragment(position)
        }
        if (position == 1) {
            fragment = AllTaskFragment(position)
        }
        if (position == 2) {
            fragment = AllTaskFragment(position)
        }
        if (position == 3) {
            fragment = AllTaskFragmentB()
        }
        return fragment
    }

}