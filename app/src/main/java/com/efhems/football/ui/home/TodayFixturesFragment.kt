package com.efhems.football.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.efhems.football.R
import com.efhems.football.ui.adapters.TodayFixTureRcAdapter
import com.efhems.football.viewmodels.HomeViewModel
import com.github.ybq.android.spinkit.SpinKitView

/**
 * A simple [Fragment] subclass.
 *
 */
class TodayFixturesFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(this.activity!!.application).create(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_today_fixtures, container, false)
        //val btn: Button = view.findViewById(R.id.btn)


        val rc: RecyclerView = view.findViewById(R.id.rc_today_fixtures)
        val spinKit: SpinKitView = view.findViewById(R.id.spin_kit)
        rc.setHasFixedSize(false)
        val adapter = TodayFixTureRcAdapter()
        rc.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        rc.adapter = adapter

        viewModel.fixtures.observe(this, Observer {
            if(it!= null  && it.isNotEmpty()){
                spinKit.visibility = GONE
                adapter.submitList(it)
            }
        })

        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        swipe.setOnRefreshListener {
            viewModel.todayfix()
        }

        return view
    }


}
