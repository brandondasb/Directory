package com.melaninwall.directory.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

class ResultFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var resultRecyclerView: RecyclerView =
        view.findViewById(R.id.result_fragment_parent_recyclerView)

}
