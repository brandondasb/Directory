package com.example.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.model.ItemGroup
import com.example.directory.viewHolder.HomeFragmentViewHolder

class HomeRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemGroups: List<ItemGroup>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_fragment, parent, false)

        return HomeFragmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemGroups.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val homeDataViewholder = viewHolder as HomeFragmentViewHolder
        val listingItemData = itemGroups[position]

        listingItemData.headerTitle
        listingItemData.listItem
        homeDataViewholder.homeRecyclerView


    }
}
