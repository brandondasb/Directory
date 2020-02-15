package com.example.directory.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R

/*contains all XML widget visible on homeFragment class
* keeping it away from the UI*/
class HomeFragmentViewHolder(view:View) :RecyclerView.ViewHolder(view){

      var homeRecyclerView :RecyclerView = view.findViewById(R.id.home_fragment_recyclerView)


init {
}

}