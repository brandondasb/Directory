package com.melaninwall.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.viewHolder.ListingSectionViewHolder

class ListingSectionAdapter(
    private val context: Context
) : RecyclerView.Adapter<ListingSectionViewHolder>() {
    private var sectionList: MutableList<Section> = mutableListOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setData(sectionList: List<Section>) {
        this.sectionList.addAll(sectionList)
        HomeScreenSectionSorter().sortList(sectionList = sectionList, sortFunction = { 1 })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingSectionViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.layout_parent_recycler, parent, false)
        return ListingSectionViewHolder(view)
    }

    override fun onBindViewHolder(
        listingSectionViewHolder: ListingSectionViewHolder,
        position: Int
    ) {
        val parentViewHolder = listingSectionViewHolder
        val listingDataGroup = sectionList[position]

        parentViewHolder.itemTitle.text = listingDataGroup.type.title
        val itemData = listingDataGroup.listItem

        val itemListAdapter = ListingRecyclerViewAdapter(context)

        itemListAdapter.setData(itemData)
        parentViewHolder.sectionRecyclerView.setHasFixedSize(true)
        parentViewHolder.sectionRecyclerView.layoutManager =
            LinearLayoutManager(
                parentViewHolder.sectionRecyclerView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        parentViewHolder.sectionRecyclerView.apply {
            parentViewHolder.sectionRecyclerView.adapter = itemListAdapter
            parentViewHolder.sectionRecyclerView.isNestedScrollingEnabled = false
            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        return sectionList.size
    }

}