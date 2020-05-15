package com.example.directory.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.directory.R
import com.example.directory.model.ListingItemData
import com.example.directory.viewHolder.ListingViewHolder
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception

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
        val postcode: String = listing.postcode
        val image = listing.image
        val category = listing.category
        listingViewHolder.name.text = name
        listingViewHolder.category.text = category.toString()//todo
        listingViewHolder.postcode.text = postcode
        Glide.with(context)
            .load(image)
            .fitCenter()
            .into(listingViewHolder.image)
    }

    override fun getItemCount(): Int {
        return listingData!!.size
    }


}
