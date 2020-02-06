package com.example.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.directory.R
import com.example.directory.interfaces.HomeFragmentPresenterListener
import com.example.directory.model.BottomNavState
import com.example.directory.presenter.HomefragmentPresenter

class HomeFragment : BaseNavFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavListener.updateBottomNav(BottomNavState.HOME)
        val homeFragmentPresenter = HomefragmentPresenter(view)
        homeFragmentPresenter.load(listOf())// change once real data comes in

    }

    fun getData() {


    }


}
