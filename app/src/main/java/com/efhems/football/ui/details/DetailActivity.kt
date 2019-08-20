package com.efhems.football.ui.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.efhems.football.databinding.ActivityDetailBinding
import com.efhems.football.ui.adapters.CompetitionsViewPagerAdapter


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)

        val binding: ActivityDetailBinding = DataBindingUtil.
            setContentView(this, com.efhems.football.R.layout.activity_detail)

        val appToolbar = binding.toolbar
        appToolbar.title = ""
        setSupportActionBar(appToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tabs.setupWithViewPager(binding.viewPager)

        intent?.let { it ->

            it.extras?.let{
                val bund = it.getBundle("bund")
                appToolbar.title = bund!!.getString("team")
                val adapter = CompetitionsViewPagerAdapter(this, supportFragmentManager, bund.getLong("id"))
                binding.viewPager.adapter = adapter
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
