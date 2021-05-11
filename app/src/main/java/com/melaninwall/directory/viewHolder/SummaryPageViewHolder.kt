package com.melaninwall.directory.viewHolder

import android.content.Context
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

    override fun bindData(data: ListingItemData?) {

        if (data != null) {
            name.text = data.name
            category.text = data.category.joinToString(" | ")

            if (data.verified) {
                verified.visibility = View.VISIBLE
            } else {
                verified.visibility = View.GONE
            }

            data.services.forEach { label ->
                if (deliveryAvailableChip.childCount < data.services.size) {
                    /** for some reason the bind function get binder function get call twice in onBinderView ListingPagerApter class, because that it was duplicated the View I programmatically created.
                    hence this condition **/
                    deliveryAvailableChip.addChip(itemView.context, label)
                }
            }
        }

        about?.text = data?.about
        data?.openingHours?.forEach {
            var openingHours = overviewOpeningHours?.text
            overviewOpeningHours?.text =
                "$openingHours ${it.dayOfWeek} - ${it.startHour} : ${it.startMinute} - ${it.endHour}  :${it.endMinute} \n"
        }
    }

    private fun ChipGroup.addChip(context: Context, label: String) {
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = false
            isFocusable = true
            addView(this)
        }
    }
}