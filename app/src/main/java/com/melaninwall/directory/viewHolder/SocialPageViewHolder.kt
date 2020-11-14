package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class SocialPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val website: TextView = itemView.findViewById(R.id.fpaWebsite)
    val twitter: TextView = itemView.findViewById(R.id.fpatwitter)
    val facebook: TextView = itemView.findViewById(R.id.fpaFacebook)
    val instagram: TextView = itemView.findViewById(R.id.fpaInstagram)
}
