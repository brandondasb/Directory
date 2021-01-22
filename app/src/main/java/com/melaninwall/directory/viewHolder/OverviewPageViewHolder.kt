package com.melaninwall.directory.viewHolder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class OverviewPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {

    val address: TextView = itemView.findViewById(R.id.listingAddress)
    val city: TextView? = itemView.findViewById(R.id.listingCity)
    val postcode: TextView? = itemView.findViewById(R.id.listingPostcode)

    val twitter: ImageView? = itemView.findViewById(R.id.overviewTwitterImageView)
    val website: ImageView? = itemView.findViewById(R.id.overviewWebsiteImageView)
    val instagram: ImageView? = itemView.findViewById(R.id.overviewInstagramImageView)
    val facebook: ImageView? = itemView.findViewById(R.id.overviewFacebook)

    override fun getViews(data: ListingItemData?) {
        address.text = data?.address
        city?.text = data?.city
        postcode?.text = data?.postcode

        address.setOnClickListener {
            val query =
                "https://www.google.com/maps/search/?api=1&query=${address.text}%2C${city?.text}%2C${postcode?.text}"
            openURL(
                it, query
            )
        }

        website?.setOnClickListener {
            val link = data?.website
            openURL(
                it, link
            )
        }

        instagram?.setOnClickListener { view ->
            data?.social?.firstOrNull { social -> social.name == "instagram" }?.url?.let {
                openURL(view, it)
            }
        }
        twitter?.setOnClickListener { view ->
            data?.social?.firstOrNull { social -> social.name == "twitter" }?.url?.let {
                openURL(view, it)
            }
        }
        facebook?.setOnClickListener { view ->
            data?.social?.firstOrNull { social -> social.name == "facebook" }?.url?.let {
                openURL(view, it)
            }
        }
    }

    private fun openURL(view: View, link: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        var fullUrl = link
        fullUrl.let {
            if (!(fullUrl?.startsWith("http://") == true || fullUrl?.startsWith("https://") == true)) {
                fullUrl = "http://$link"
            }
            intent.data = Uri.parse(fullUrl)
            view.context.startActivity(intent)
        }
    }
}