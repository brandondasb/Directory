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
import com.melaninwall.directory.viewHolder.ListingViewHolder

class ListingRecyclerViewAdapter(
    private val context: Context,
    private val itemListener: ListItemListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listingData: List<ListingItemData>? = listOf()

    fun setData(listingData: List<ListingItemData>?) {
        this.listingData = listingData
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_home_card_child_recycler, viewGroup, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val listingViewHolder: ListingViewHolder = viewHolder as ListingViewHolder
        val listing: ListingItemData = this.listingData!![position]

        listingViewHolder.homeRootLayout.setOnClickListener {
            itemListener.launchFragment(listing)
        }
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

    override fun getItemCount(): Int {
        return listingData!!.size
    }


}
