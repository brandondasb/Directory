package com.example.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.model.ItemGroup
import com.example.directory.viewHolder.ListingItemGroupViewHolder

class ListingItemGroupAdapter(private val context: Context) :
    RecyclerView.Adapter<ListingItemGroupViewHolder>() {

    private lateinit var dataGroupList: List<ItemGroup>

    fun setData(listingData: List<ItemGroup>) {
        this.dataGroupList = dataGroupList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingItemGroupViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_group, parent, false)
        return ListingItemGroupViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ListingItemGroupViewHolder, position: Int) {
        var listingItemGroupViewHolder = viewHolder
        var listingDataGroup = dataGroupList[position]

        listingItemGroupViewHolder.itemTitle.text = listingDataGroup.headerTitle
        var items = listingDataGroup.listItem

        var itemListAdapter = ListingRecyclerViewAdapter(context)


        itemListAdapter.setData(items)
        listingItemGroupViewHolder.groupRecyclerView.setHasFixedSize(true)
        listingItemGroupViewHolder.groupRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listingItemGroupViewHolder.groupRecyclerView.adapter = itemListAdapter
        listingItemGroupViewHolder.groupRecyclerView.isNestedScrollingEnabled = false


    }

    override fun getItemCount(): Int {
        return dataGroupList.size
    }

}