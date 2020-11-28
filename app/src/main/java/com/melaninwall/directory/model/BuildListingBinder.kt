package com.melaninwall.directory.model

import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.viewHolder.SocialPageViewHolder
import com.melaninwall.directory.viewHolder.SummaryPageViewHolder

class BuildListingBinder(
    val viewHolder: RecyclerView.ViewHolder,
    private val dataModel: ListingItemData?
) {

    fun bind() {

        when (viewHolder) {
            is SocialPageViewHolder -> bindContactSection(viewHolder)
            is SummaryPageViewHolder -> bindAboutSection(viewHolder, dataModel?.about)
        }
    }

    private fun bindAboutSection(view: SummaryPageViewHolder, about: String?) {
        view.about?.text = about
    }

    private fun bindContactSection(
        view: SocialPageViewHolder
    ) {
        view.website?.text = dataModel?.website
        view.facebook?.text = dataModel?.social?.firstOrNull { it.name == "facebook" }?.url
        view.instagram?.text = dataModel?.social?.firstOrNull { it.name == "instagram" }?.url
        view.twitter?.text = dataModel?.social?.firstOrNull { it.name == "twitter" }?.url

    }
}