package com.efhems.football.ui.adapters

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.efhems.football.ui.details.FixtureFragment
import com.efhems.football.ui.details.TableFragment
import com.efhems.football.ui.details.TeamsFragment


class CompetitionsViewPagerAdapter(private val mContext: Context, fm: FragmentManager, val id: Long) : FragmentPagerAdapter(fm) {

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    override fun getItem(position: Int): Fragment {


        val bundle = Bundle()
        bundle.putLong("id", id)
        return when (position) {
            0 -> {
                val tab = TableFragment()
                tab.arguments = bundle
                tab
            }
            1 -> {
                val tab = FixtureFragment()
                tab.arguments = bundle
                tab
            }
            else -> {
                val tab = TeamsFragment()
                tab.arguments = bundle
                tab
            }
        }
    }

    /**
     * Return the number of views available.
     */
    override fun getCount(): Int {
        return 3
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Table"
            1 -> "Fixtures"
            2 -> "Teams"
            else -> null
        }
    }
}