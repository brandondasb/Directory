package com.example.directory.presenter;

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.adapters.ListingRecyclerViewAdapter
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View) : ListingCallback {

    private val context = itemView.context
    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var listingRecyclerViewAdapter = ListingRecyclerViewAdapter(context)

    init {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingRecyclerViewAdapter = ListingRecyclerViewAdapter(context)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingRecyclerViewAdapter

        //configureSpinner()
    }

    override fun loadAllListingItemData(listingItemData: List<ListingItemData>) {
        listingRecyclerViewAdapter.setData(listingItemData)
    }

}


