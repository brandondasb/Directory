package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class OverviewPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {


    val website: TextView? = itemView.findViewById(R.id.overviewWebsite)
    val twitter: TextView? = itemView.findViewById(R.id.overviewTwitter)
    val facebook: TextView? = itemView.findViewById(R.id.overviewFacebook)
    val instagram: TextView? = itemView.findViewById(R.id.overviewInstagram)
    val address: TextView = itemView.findViewById(R.id.listingAddress)
    val city: TextView? = itemView.findViewById(R.id.listingCity)
    val postcode: TextView? = itemView.findViewById(R.id.listingPostcode)

    override fun getViews(data: ListingItemData?) {
        website?.text = data?.website
        facebook?.text = data?.social?.firstOrNull { it.name == "facebook" }?.url
        instagram?.text = data?.social?.firstOrNull { it.name == "instagram" }?.url
        twitter?.text = data?.social?.firstOrNull { it.name == "twitter" }?.url
        address.text = data?.address
        city?.text = data?.city
        postcode?.text = data?.postcode
    }

    fun clickListener(view: View, data: ListingItemData) {


    }
}