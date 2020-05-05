package com.example.directory.presenter;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.R
import com.example.directory.adapters.ListItemListener
import com.example.directory.adapters.ListingItemGroupAdapter
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData
import com.example.directory.view.ListingFragment
import com.example.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View, var fragmentManager: FragmentManager?) :
    ListingCallback,
    ListItemListener {

    private val context = itemView.context

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var listingItemGroupAdapter = ListingItemGroupAdapter(context, this)

    init {
        //configureSpinner()
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingItemGroupAdapter = ListingItemGroupAdapter(context, this)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingItemGroupAdapter
    }

    override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {

        listingItemGroupAdapter.setData(listingItemGroupData)
    }

    override fun loadLondon(listingItemGroupData: List<ItemGroup>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun launchFragment(itemData: ListingItemData) {
        val bundle = Bundle()
        bundle.putSerializable("listingItemData", itemData)
        val listingFragment = ListingFragment()
        listingFragment.arguments = bundle

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }


}


