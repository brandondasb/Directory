package com.example.directory.presenter;

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.adapters.ListingItemGroupAdapter
import com.example.directory.adapters.ListingRecyclerViewAdapter
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View) : ListingCallback {
    override fun loadLondon(listingItemGroupData: List<ItemGroup>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val context = itemView.context

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var listingItemGroupAdapter = ListingItemGroupAdapter(context)

    init {
        //configureSpinner()
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingItemGroupAdapter = ListingItemGroupAdapter(context)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingItemGroupAdapter
    }

    override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {

        listingItemGroupAdapter.setData(listingItemGroupData)
    }

}


