package com.melaninwall.directory.viewHolder

import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.melaninwall.directory.R

/*contains all XML widget visible on homeFragment class
* keeping it away from the UI*/
class HomeFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var homeRecyclerView: RecyclerView = view.findViewById(R.id.homeFragmentParentRecyclerView)
    var categoryRecyclerView: RecyclerView = view.findViewById(R.id.category_recyclerView)

}