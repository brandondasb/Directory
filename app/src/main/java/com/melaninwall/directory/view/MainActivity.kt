package com.melaninwall.directory.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.UpdateBottomNavListener
import com.melaninwall.directory.model.BottomNavState
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private var fragmentManager: FragmentManager = supportFragmentManager

    val bottomNavListener: UpdateBottomNavListener = object : UpdateBottomNavListener {
        override fun updateBottomNav(currentState: BottomNavState) {
            val bottomNavigationView: BottomNavigationView =
                findViewById(R.id.bottom_navigation)
            when (currentState) {
                BottomNavState.HOME -> bottomNavigationView.selectedItemId = R.id.nav_home
                BottomNavState.SEARCH -> bottomNavigationView.selectedItemId = R.id.nav_search
                BottomNavState.THIRD -> bottomNavigationView.selectedItemId = R.id.nav_inbox
                BottomNavState.FOURTH -> bottomNavigationView.selectedItemId = R.id.nav_profile

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar?.hide()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener(navlistener)
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        val homeFragment = HomeFragment()
        homeFragment.setUpdateBottomNavListener(bottomNavListener)

        fragmentTransaction.replace(R.id.fragment_container, homeFragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_bottom_navigation_bar, menu)
        return true

    }

    /* create listener for the bottom Nav*/
    private val navlistener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val selectedFragment: BaseNavFragment? = when (item.itemId) {
                    R.id.nav_home -> if (fragmentManager.findFragmentByTag(HomeFragment::class.java.name) != null) {
                        fragmentManager.findFragmentByTag(HomeFragment::class.java.name) as? HomeFragment
                    } else {
                        HomeFragment() // I had to make the home class Open
                    }
                    R.id.nav_search -> if (fragmentManager.findFragmentByTag(SecondFragment::class.java.name) != null) {
                        fragmentManager.findFragmentByTag(SecondFragment::class.java.name) as? SecondFragment
                    } else {
                        SecondFragment()
                    }
                    R.id.nav_inbox -> if (fragmentManager.findFragmentByTag(ThirdFragment::class.java.name) != null) {
                        fragmentManager.findFragmentByTag(ThirdFragment::class.java.name) as? ThirdFragment
                    } else {
                        ThirdFragment()
                    }
                    R.id.nav_profile -> if (fragmentManager.findFragmentByTag(FourthFragment::class.java.name) != null) {
                        fragmentManager.findFragmentByTag(FourthFragment::class.java.name) as? FourthFragment
                    } else {
                        FourthFragment()
                    }
                    else -> {
                        null
                    }
                }

                selectedFragment?.let { fragment ->
                    fragment.setUpdateBottomNavListener(bottomNavListener)
                    return loadFragment(fragment)
                } ?: run {
                    return false
                }
            }

        }

    fun onGroupItemClick(item: MenuItem) {

    }

    private fun loadFragment(fragment: BaseNavFragment): Boolean {
        if (isNotInBackStack(fragmentManager, fragment)) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment::class.java.name)
                .addToBackStack(fragment::class.java.name)
                .commitAllowingStateLoss()
        } else {
            fragmentManager.popBackStack(fragment::class.java.name, 0)
        }
        return true
    }

    /*confirm if there is anything in the backstack Queue*/
    fun isNotInBackStack(
        fragmentManager: FragmentManager,
        existingFragment: Fragment?
    ): Boolean {// can this be null????

        var result = true
        if (existingFragment != null) {

            val backStackSize = fragmentManager.backStackEntryCount

            for (i in 0 until backStackSize) {
                val backStackEntry: FragmentManager.BackStackEntry =
                    fragmentManager.getBackStackEntryAt(i)
                if (existingFragment::class.java.name == backStackEntry.name) {
                    result = false
                    break
                }
            }
        }
        return result
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack(HomeFragment::class.java.name, 0)
        } else {
            this.finish()
        }
    }
}



