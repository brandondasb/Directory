package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R
import com.melaninwall.directory.model.ListingItemData

class SummaryPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    PageViewGenerator {
    val about: TextView? = itemView.findViewById(R.id.summaryAbout)
    val overviewOpeningHours: TextView? = itemView.findViewById(R.id.overviewOpeningHours)
    // val deliveryAvailableChip:Chip = itemView.findViewById(R.id.overviewDelivery)


    override fun getViews(data: ListingItemData?) {
        about?.text = data?.about
        data?.openingHours?.forEach {
            var openingHours = overviewOpeningHours?.text
            overviewOpeningHours?.text =
                "$openingHours ${it.dayOfWeek} - ${it.startHour} : ${it.startMinute} - ${it.endHour}  :${it.endMinute} \n"
        }
    }
}