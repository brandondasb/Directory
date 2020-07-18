package com.melaninwall.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.viewHolder.DiscoverListingViewHolder

class DiscoverRecyclerViewAdapter(
    private val context: Context,
    private val itemListener: ListItemListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listItemData: List<ListingItemData> = listOf()

    fun setData(dataList: List<ListingItemData>) {
        this.listItemData = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_discover_card_recycler, parent, false)

        return DiscoverListingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItemData.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val searchFragmentViewHolder = viewHolder as DiscoverListingViewHolder
        val listingItemData = listItemData[position]
        searchFragmentViewHolder.homeRootLayout.setOnClickListener {
            itemListener.launchFragment(listingItemData)
        }
        val name = listingItemData.name
        val image = listingItemData.image
        val city = listingItemData.city
        val category = listingItemData.category
        val verified = listingItemData.verified

        searchFragmentViewHolder.name.text = name
        searchFragmentViewHolder.city.text = city
        searchFragmentViewHolder.category.text = category.joinToString(" | ")

        if (verified) {
            searchFragmentViewHolder.verified.visibility = View.VISIBLE
        } else {
            searchFragmentViewHolder.verified.visibility = View.GONE
        }
        Glide.with(context)
            .load(image).centerCrop()
            .into(searchFragmentViewHolder.image)
    }
}
