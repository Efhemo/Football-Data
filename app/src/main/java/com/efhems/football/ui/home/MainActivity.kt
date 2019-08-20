package com.efhems.football.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.efhems.football.R
import com.efhems.football.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val bottomNavigationView = binding.bottomNavigation
        val appToolbar = binding.toolbar
        setSupportActionBar(appToolbar)
        //appToolbar.setTitleTextColor(resources.getColor(R.color.white))

        val topLevel = HashSet<Int>()
        topLevel.add(R.id.todayFixturesFragment)
        topLevel.add(R.id.competitionsFragment)

        //Setting up toolbar, appbarconf, toplevel with nav takes care of the upbutton
        appBarConfiguration = AppBarConfiguration.Builder(topLevel).build()
        navController = Navigation.findNavController(this, R.id.fragment_host)
        NavigationUI.setupWithNavController(appToolbar, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        /*navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.detailFragment -> bottomNavigationView.visibility = GONE
            }
        }*/
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item)

        //return super.onOptionsItemSelected(item);
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
