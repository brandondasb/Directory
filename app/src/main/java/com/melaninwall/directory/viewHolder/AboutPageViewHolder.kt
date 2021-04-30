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
    private val city: TextView? = itemView.findViewById(R.id.listingCity)
    private val postcode: TextView? = itemView.findViewById(R.id.listingPostcode)

    private val twitterCta: View? = itemView.findViewById(R.id.listingTwitterCTA)
    private val websiteCta: View? = itemView.findViewById(R.id.listingWebisteCTA)
    private val instagramCta: View? = itemView.findViewById(R.id.listingInstagramCTA)

    private val numberCta: View? = itemView.findViewById(R.id.listingNumberCTA)

    override fun getViews(data: ListingItemData?) {
        address.text = data?.address
        city?.text = data?.city
        // without postcode it will hide the entire block// dodgy logic
        data?.postcode?.let {
            postcode?.text = it
        } ?: kotlin.run {
            addressContainer.visibility = View.GONE
        }

        data?.social?.firstOrNull { social -> social.name == "instagram" }?.url?.let {
            instagramCta?.setOnClickListener { view ->
                data.social.firstOrNull { social -> social.name == "instagram" }?.url?.let {
                    openURL(view, it)
                }
            }
        } ?: run {
            instagramCta?.visibility = View.GONE
        }

        data?.social?.firstOrNull { social -> social.name == "twitter" }?.url?.let {
            twitterCta?.setOnClickListener { view ->
                data.social.firstOrNull { social -> social.name == "twitter" }?.url?.let {
                    openURL(view, it)
                }
            }
        } ?: run {
            twitterCta?.visibility = View.GONE
        }

        addressContainer.setOnClickListener {
            it.startAnimation(AnimationUtils.loadAnimation(it.context, R.anim.icon_click))
            val query =
                "https://www.google.com/maps/search/?api=1&query=${address.text}%2C${city?.text}%2C${postcode?.text}"
            openURL(
                it, query
            )
        }

        if (data?.phoneNumber == -1) {
            numberCta?.visibility = View.GONE
        } else {
            numberCta?.visibility = View.VISIBLE
            numberCta?.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                val number = data?.phoneNumber
                intent.data = Uri.parse("tel: 0$number")
                it.context.startActivity(intent)
            }
        }

        websiteCta?.setOnClickListener {
            val link = data?.website
            openURL(
                it, link
            )
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