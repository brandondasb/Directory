package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class SummaryPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {
    val about: TextView? = itemView.findViewById(R.id.summaryAbout)


    override fun getViews(data: ListingItemData?) {
        about?.text = data?.about
    }
}