package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class SummaryPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {
    val name: TextView = itemView.findViewById(R.id.listingNameTextView)
    val verified: ImageView = itemView.findViewById(R.id.listingVerifiedListingImageView)
    val category: TextView = itemView.findViewById(R.id.listingCategoryTextView)
    val about: TextView? = itemView.findViewById(R.id.summaryAbout)
    val overviewOpeningHours: TextView? = itemView.findViewById(R.id.overviewOpeningHours)

    val deliveryAvailableChip: ChipGroup = itemView.findViewById(R.id.summarySomethingChip)
    override fun getViews(data: ListingItemData?) {

        if (data != null) {
            name.text = data.name
            category.text = data.category.joinToString(" | ")

            if (data.verified) {
                verified.visibility = View.VISIBLE
            } else {
                verified.visibility = View.GONE
            }

            for (service in data.services) {
                var chip = Chip(itemView.context)
                chip.text = service

                deliveryAvailableChip.addView(chip)
            }
        }

        about?.text = data?.about
        data?.openingHours?.forEach {
            var openingHours = overviewOpeningHours?.text
            overviewOpeningHours?.text =
                "$openingHours ${it.dayOfWeek} - ${it.startHour} : ${it.startMinute} - ${it.endHour}  :${it.endMinute} \n"
        }
    }
}