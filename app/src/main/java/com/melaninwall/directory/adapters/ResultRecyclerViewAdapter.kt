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
import com.melaninwall.directory.viewHolder.LoadingSearchViewHolder
import com.melaninwall.directory.viewHolder.SearchListingViewHolder

// using same view holder as search
class ResultRecyclerViewAdapter(
    private val context: Context,
    private val itemListener: ListItemListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemDataList: List<ListingItemData> = listOf()
    private var isLoading: Boolean = true
    private val LOADING_CELL_COUNT = 20
    private val LOADING_CELL_TYPE = 0
    private val LISTING_CELL = 1

    fun setData(dataList: List<ListingItemData>) {
        this.itemDataList = dataList
        isLoading = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == LOADING_CELL_TYPE) {
            val view: View = LayoutInflater.from(context)
                .inflate(R.layout.list_item_search_card_recycler_placeholder, viewGroup, false)
            LoadingSearchViewHolder(view)
        } else {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_search_card_recycler, viewGroup, false)
            SearchListingViewHolder(view) // could have its own in the future if data need to be different
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) {
            LOADING_CELL_COUNT
        } else {
            itemDataList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading) {
            LOADING_CELL_TYPE
        } else {
            LISTING_CELL
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == LISTING_CELL) {
            val searchFragmentViewHolder = viewHolder as SearchListingViewHolder
            val listingItemData = itemDataList[position]

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
}

