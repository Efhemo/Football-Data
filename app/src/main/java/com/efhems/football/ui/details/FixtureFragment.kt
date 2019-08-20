package com.efhems.football.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.efhems.football.R
import com.github.ybq.android.spinkit.SpinKitView

class FixtureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_competitions, container, false)

        val rc: RecyclerView = view.findViewById(R.id.rc_competition)
        val spinKit: SpinKitView = view.findViewById(R.id.spin_kit)
        rc.visibility = GONE
        spinKit.visibility = GONE

        val swipeToRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)

        swipeToRefresh.setOnRefreshListener {

            swipeToRefresh.isRefreshing = false
        }
        return view
    }


}