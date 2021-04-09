package com.melaninwall.directory.view

import android.content.Context
import androidx.fragment.app.Fragment

open class NestedFragment : Fragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()// this don't work well when rootFragment/NestedFragment/NestedFragment and you press back from 1 fragment to an child Fragment
        super.onDetach()
    }
}