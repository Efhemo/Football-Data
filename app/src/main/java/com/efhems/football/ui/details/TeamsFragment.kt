package com.efhems.football.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.efhems.football.R
import com.efhems.football.ui.adapters.TeamRCAdapter
import com.efhems.football.viewmodels.DetailViewModel
import com.github.ybq.android.spinkit.SpinKitView

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_competitions, container, false)

        val swipeToRefresh: SwipeRefreshLayout = view.findViewById(R.id.swipe_refresh)

        val rc: RecyclerView = view.findViewById(R.id.rc_competition)

        val image: AppCompatImageView = view.findViewById(R.id.ball)
        val noFixture: TextView = view.findViewById(R.id.no_fixture)
        val retryBtn: AppCompatButton = view.findViewById(R.id.btn_retry)
        val spinKitView: SpinKitView = view.findViewById(R.id.spin_kit)


        image.visibility = GONE
        noFixture.visibility = GONE
        retryBtn.visibility = GONE

        rc.setHasFixedSize(false)
        rc.layoutManager = GridLayoutManager(this.requireContext(), 3)
        val adapter = TeamRCAdapter()
        rc.adapter = adapter

        arguments?.let { it ->

            val viewModel =

                ViewModelProvider(this, DetailViewModel.Factory(activity!!.application, it.getLong("id"))).get(
                    DetailViewModel::class.java
                )

            viewModel.teams.observe(this, Observer {

                if(it != null && it.isNotEmpty()){
                    spinKitView.visibility = GONE
                    adapter.submitList(it)

                }
            })

            swipeToRefresh.setOnRefreshListener {
                viewModel.table()
                swipeToRefresh.isRefreshing = false
            }
        }

        return view
    }


}