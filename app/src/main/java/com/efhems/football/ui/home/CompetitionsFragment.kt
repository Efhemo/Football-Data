package com.efhems.football.ui.home


import android.content.Intent
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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.efhems.football.model.Competitions
import com.efhems.football.ui.adapters.CompetitionsRCAdapter
import com.efhems.football.ui.details.DetailActivity
import com.efhems.football.viewmodels.HomeViewModel
import com.github.ybq.android.spinkit.SpinKitView


class CompetitionsFragment : Fragment(), CompetitionsRCAdapter.OnCompClickListener {



    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider.AndroidViewModelFactory(this.activity!!.application).create(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(com.efhems.football.R.layout.fragment_competitions, container, false)


        val rc: RecyclerView = view.findViewById(com.efhems.football.R.id.rc_competition)

        val image: AppCompatImageView = view.findViewById(com.efhems.football.R.id.ball)
        val noFixture: TextView = view.findViewById(com.efhems.football.R.id.no_fixture)
        val retryBtn: AppCompatButton = view.findViewById(com.efhems.football.R.id.btn_retry)
        val spinKitView: SpinKitView = view.findViewById(com.efhems.football.R.id.spin_kit)
        image.visibility = GONE
        noFixture.visibility = GONE
        retryBtn.visibility = GONE

        rc.setHasFixedSize(false)
        val adapter = CompetitionsRCAdapter(this)
        rc.layoutManager = LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL ,false)
        rc.addItemDecoration(DividerItemDecoration(context!!, DividerItemDecoration.VERTICAL))
        rc.adapter = adapter

        viewModel.allComp.observe(this, Observer {
            if(it.isNotEmpty()){
                spinKitView.visibility = GONE
                adapter.submitList(it)
            }
        })

        val swipe = view.findViewById<SwipeRefreshLayout>(com.efhems.football.R.id.swipe_refresh)
        swipe.setOnRefreshListener{
            viewModel.compettions()
            swipe.isRefreshing = false
        }

        return view
    }

    override fun onClick(comp: Competitions) {

        val intent = Intent(activity, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putLong("id", comp.id)
        bundle.putString("team", comp.name)

        intent.putExtra("bund", bundle)
        startActivity(intent)
    }

}
