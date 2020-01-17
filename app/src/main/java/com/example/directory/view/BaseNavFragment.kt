package com.example.directory.view

import androidx.fragment.app.Fragment
import com.example.directory.interfaces.UpdateBottomNavListener

abstract class BaseNavFragment : Fragment() {
   lateinit var bottomNavListener :UpdateBottomNavListener;

    fun setUpdateBottomNavListener(updateBottomNavListener: UpdateBottomNavListener) {
        this.bottomNavListener=updateBottomNavListener

    }
}