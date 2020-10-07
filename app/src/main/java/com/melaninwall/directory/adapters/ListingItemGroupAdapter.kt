package com.melaninwall.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.model.ItemGroup
import com.melaninwall.directory.viewHolder.ListingItemGroupViewHolder

class ListingItemGroupAdapter(
    private val context: Context,
    private val listener: ListItemListener
) :
    RecyclerView.Adapter<ListingItemGroupViewHolder>() {
    private var dataGroupList: MutableList<ItemGroup> = mutableListOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(dataGroupList: List<ItemGroup>) {
        this.dataGroupList.addAll(dataGroupList)
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