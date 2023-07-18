package com.voleti.multi_module

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.voleti.details.DetailsFragment
import com.voleti.details.DetailsFragmentDirections
import com.voleti.multi_module.databinding.ActivityMainBinding
import com.voleti.token_manager.TokenManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tokenManager by lazy {
        TokenManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navHostFragment = setupLoginFlow(tokenManager.getToken().isNotBlank())
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
           when(destination.id) {
              com.voleti.home.R.id.homeFragment,com.voleti.dashboard.R.id.dashboardFragment, com.voleti.notifications.R.id.notificationsFragment -> {
                  navView.visibility = View.VISIBLE
              }
              else -> {
                  navView.visibility = View.GONE
              }
           }
        }

        navController.addOnDestinationChangedListener{ _, destination, _ ->

            when(destination.id){
                R.id.loginFragment,R.id.detailsFragment -> {
                    supportActionBar?.hide()
                }
                else -> {
                    supportActionBar?.show()
                }
            }

        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupLoginFlow(flag:Boolean): NavHostFragment{
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.mobile_navigation)
        graph.setStartDestination(if(flag) R.id.navigation_home else R.id.loginFragment)
        navHostFragment.navController.graph = graph
        return navHostFragment
    }
}