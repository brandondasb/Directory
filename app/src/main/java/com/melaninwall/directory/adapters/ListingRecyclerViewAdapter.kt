package com.melaninwall.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.HomeFragment
import com.melaninwall.directory.viewHolder.ListingViewHolder
import com.melaninwall.directory.viewHolder.LoadingSearchViewHolder

class ListingRecyclerViewAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val fragment = HomeFragment()
    private var listingData: List<ListingItemData>? = listOf()
    private var isLoading: Boolean = true
    private val LOADING_CELL_COUNT = 20
    private val LOADING_CELL_TYPE = 0
    private val LISTING_CELL = 1

    fun setData(listingData: List<ListingItemData>?) {
        this.listingData = listingData
        isLoading = false
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == LOADING_CELL_TYPE) {
            val view: View = LayoutInflater.from(context)
                .inflate(R.layout.list_item_home_card_child_recycler_placeholder, viewGroup, false)
            LoadingSearchViewHolder(view)
        } else {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.list_item_home_card_child_recycler, viewGroup, false)
            ListingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) {
            LOADING_CELL_COUNT
        } else {
            return listingData!!.size
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

            val listingViewHolder: ListingViewHolder = viewHolder as ListingViewHolder
            val listing: ListingItemData = listingData!![position] //TODO Double bang

            fragment.launchFragment(
                listingViewHolder.homeRootLayout,
                listingData!![position]
            ) // fix it man

            val name: String = listing.name
            val about: String = listing.about
            val city: String = listing.city
            val image = listing.image
            val category = listing.category
            val verified = listing.verified

            if (verified) {
                listingViewHolder.verified.visibility = View.VISIBLE
            } else {
                listingViewHolder.verified.visibility = View.GONE
            }

            listingViewHolder.name.text = name
            listingViewHolder.category.text = category.joinToString(" | ")
            listingViewHolder.city.text = city
            Glide.with(context)
                .load(image)
                .fitCenter()
                .into(listingViewHolder.image)
        }
    }
}
