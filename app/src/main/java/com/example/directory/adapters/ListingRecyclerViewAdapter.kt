package com.example.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.ListingViewHolder

class ListingRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listingData: List<ListingItemData>? = listOf()

    fun setData(listingData: List<ListingItemData>?) {
        this.listingData = listingData
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_home_card, viewGroup, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val listingViewHolder: ListingViewHolder = viewHolder as ListingViewHolder
        val listing: ListingItemData = this.listingData!![position]

        val name: String = listing.name
        val about: String = listing.about
        val postcode: String = listing.postcode
        //  val image: Drawable = listing.image
        val category: String = listing.category
        listingViewHolder.name.text = name
        listingViewHolder.category.text = category
        listingViewHolder.postcode.text = postcode
        // listingViewHolder.image.drawable = image
    }

    override fun getItemCount(): Int {
        return listingData!!.size
    }


}