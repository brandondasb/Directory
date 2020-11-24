package com.melaninwall.directory.model

import android.view.View
import com.melaninwall.directory.viewHolder.SocialPageViewHolder
import com.melaninwall.directory.viewHolder.SummaryPageViewHolder

class BuildListingBinder {

    fun bind(view: View, data: ListingItemData?) {
        bindAboutSection(view, data?.about)
        bindContactSection(
            view, data?.website, data?.social?.firstOrNull { it.name == "facebook" }?.url,
            data?.social?.firstOrNull { it.name == "instagram" }?.url,
            data?.social?.firstOrNull { it.name == "twitter" }?.url
        )
    }

    private fun bindAboutSection(view: View, about: String?) {
        SummaryPageViewHolder(view).about?.text = about
    }

    private fun bindContactSection(
        view: View,
        site: String?,
        facebook: String?,
        instagram: String?,
        twitter: String?
    ) {
        SocialPageViewHolder(view).website?.text = site
        SocialPageViewHolder(view).facebook?.text = facebook
        SocialPageViewHolder(view).instagram?.text = instagram
        SocialPageViewHolder(view).twitter?.text = twitter
    }
}