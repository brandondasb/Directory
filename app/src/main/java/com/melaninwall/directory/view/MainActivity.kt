package com.melaninwall.directory.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.LoginAuthorisation
import com.melaninwall.directory.repo.AuthEmailPassword
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var loginAuthorisation: LoginAuthorisation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginAuthorisation = AuthEmailPassword()
//        loginAuthorisation.updateUI()
        setupViews()
    }


    private fun setupViews() {
        //find the navigation controller
        val navController = findNavController(R.id.fragNavHost)

        // setting navigation controller with the bottomNavigationView
        val bottomNavView: BottomNavigationView = findViewById(R.id.bottomNavView)
        bottomNavView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.thirdFragment,
                R.id.profileFragment
            )
        )
        //setting up actionBar with Navigation Controller
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    fun showBottomNavigation() {
        bottomNavView.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        bottomNavView.visibility = View.GONE
    }

    private var backPressedOnce = false

    override fun onBackPressed() {
        val navController = findNavController(R.id.fragNavHost)
        //Check if the current destination is actually the start destination (Home screen)
        if (navController.graph.startDestination == navController.currentDestination?.id) {
            //Check if back is already pressed, if yes, then exit the app.
            if (backPressedOnce) {
                super.onBackPressed()
                return
            }

            backPressedOnce = true
            Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show()

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                backPressedOnce = false

            }, 2000)
        } else {
            super.onBackPressed()
        }
    }
}



