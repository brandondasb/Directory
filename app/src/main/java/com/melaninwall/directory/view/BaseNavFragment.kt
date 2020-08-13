package com.melaninwall.directory.view

import androidx.fragment.app.Fragment
import com.melaninwall.directory.interfaces.UpdateBottomNavListener

abstract class BaseNavFragment : Fragment() {
    companion object {
        fun create(updateBottomNavListener: UpdateBottomNavListener) {
      //      this.bottomNavListener = updateBottomNavListener
        }

//        lateinit var bottomNavListener: UpdateBottomNavListener
    }
}