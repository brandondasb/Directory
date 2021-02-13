package com.melaninwall.directory.viewHolder

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class AboutPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {
    private val addressContainer: View = itemView.findViewById(R.id.listingAddressContainer)
    private val address: TextView = itemView.findViewById(R.id.listingAddress)
    val city: TextView? = itemView.findViewById(R.id.listingCity)
    private val postcode: TextView? = itemView.findViewById(R.id.listingPostcode)

    private val twitter: TextView? = itemView.findViewById(R.id.listingTwitter)
    private val website: TextView? = itemView.findViewById(R.id.listingWebsite)
    private val instagram: TextView? = itemView.findViewById(R.id.listingInstagram)

    private val twitterCta: View? = itemView.findViewById(R.id.listingTwitterCTA)
    private val websiteCta: View? = itemView.findViewById(R.id.listingWebisteCTA)
    private val instagramCta: View? = itemView.findViewById(R.id.listingInstagramCTA)

    // private val facebookCta: View?
    private val numberCta: View? = itemView.findViewById(R.id.listingNumberCTA)

    override fun getViews(data: ListingItemData?) {
        address.text = data?.address
        city?.text = data?.city
        postcode?.text = data?.postcode
        twitter?.text = data?.social?.firstOrNull { social -> social.name == "twitter" }?.url
        instagram?.text = data?.social?.firstOrNull { social -> social.name == "instagram" }?.url
        website?.text = data?.website


        addressContainer.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.icon_click))
            val query =
                "https://www.google.com/maps/search/?api=1&query=${address.text}%2C${city?.text}%2C${postcode?.text}"
            openURL(
                it, query
            )
        }

        websiteCta?.setOnClickListener {
            val link = data?.website
            openURL(
                it, link
            )
        }

        instagramCta?.setOnClickListener { view ->
            data?.social?.firstOrNull { social -> social.name == "instagram" }?.url?.let {
                openURL(view, it)
            }
        }
        twitterCta?.setOnClickListener { view ->
            data?.social?.firstOrNull { social -> social.name == "twitter" }?.url?.let {
                openURL(view, it)
            }
        }
//        facebookCta?.setOnClickListener { view ->
//            data?.social?.firstOrNull { social -> social.name == "facebook" }?.url?.let {
//                openURL(view, it)
//            }
//        }
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