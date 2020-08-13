package com.melaninwall.directory.presenter

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.ResultRecyclerViewAdapter
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.ResultFragmentViewHolder

class ResultFragmentPresenter(itemView: View, var fragmentManager: FragmentManager?) :
    ListItemListener, SearchListingCallBack {
    private val context = itemView.context

    private val resultFragmentViewHolder = ResultFragmentViewHolder(itemView)
    private var resultRecyclerViewAdapter = ResultRecyclerViewAdapter(context, this)


    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        resultFragmentViewHolder.resultRecyclerView.layoutManager = linearLayoutManager

        resultRecyclerViewAdapter = ResultRecyclerViewAdapter(context, this)
    }

    override fun loadItemData(listingItemData: List<ListingItemData>) {
        resultRecyclerViewAdapter.setData(listingItemData)
    }

    override fun launchFragment(itemData: ListingItemData) {
        val listingFragment = ListingFragment.create(itemData)

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

}