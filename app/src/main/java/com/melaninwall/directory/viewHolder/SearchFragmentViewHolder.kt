package com.melaninwall.directory.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class SearchFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var searchRecyclerView: RecyclerView =
        view.findViewById(R.id.search_fragment_parent_recyclerView)
}