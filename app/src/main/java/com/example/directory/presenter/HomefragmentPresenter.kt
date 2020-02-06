package com.example.directory.presenter;

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.directory.adapters.ListingRecyclerViewAdapter
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.HomeFragmentViewHolder

class HomefragmentPresenter(itemView: View) {

    private val context = itemView.context
    private val viewHolder = HomeFragmentViewHolder(itemView)
    private var listingRecyclerViewAdapter = ListingRecyclerViewAdapter(context)

    init {
        //viewHolder get recyclerview and set adapter
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        viewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingRecyclerViewAdapter = ListingRecyclerViewAdapter(context)
        viewHolder.homeRecyclerView.adapter= listingRecyclerViewAdapter

        //configureSpinner()
    }
     fun load(homeListing:List<ListingItemData>){
         listingRecyclerViewAdapter.setData(homeListing)
     }

}


