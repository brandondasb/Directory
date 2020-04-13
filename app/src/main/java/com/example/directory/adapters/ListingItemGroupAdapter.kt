package com.example.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.ListingItemGroupViewHolder

class ListingItemGroupAdapter(
    private val context: Context,
    private val listener: ListItemListener
) :
    RecyclerView.Adapter<ListingItemGroupViewHolder>() {

    private var dataGroupList: List<ItemGroup> = listOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(dataGroupList: List<ItemGroup>) {
        this.dataGroupList = dataGroupList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingItemGroupViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_parent_recycler, parent, false)
        return ListingItemGroupViewHolder(view)
    }

    override fun onBindViewHolder(
        listingItemGroupViewHolder: ListingItemGroupViewHolder,
        position: Int
    ) {
        var parentViewHolder = listingItemGroupViewHolder
        var listingDataGroup = dataGroupList[position]

        parentViewHolder.itemTitle.text = listingDataGroup.headerTitle
        var itemData = listingDataGroup.listItem


        var itemListAdapter = ListingRecyclerViewAdapter(context, itemListener = listener)

        itemListAdapter.setData(itemData)
        parentViewHolder.groupRecyclerView.setHasFixedSize(true)
        parentViewHolder.groupRecyclerView.layoutManager =
            LinearLayoutManager(
                parentViewHolder.groupRecyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        parentViewHolder.groupRecyclerView.apply {
            parentViewHolder.groupRecyclerView.adapter = itemListAdapter
            parentViewHolder.groupRecyclerView.isNestedScrollingEnabled = false
            setRecycledViewPool(viewPool)
        }

    }

    override fun getItemCount(): Int {
        return dataGroupList.size
    }

}

interface ListItemListener {
    fun launchFragment(itemData: ListingItemData)

}