package com.melaninwall.directory.presenter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.adapters.ResultRecyclerViewAdapter
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.viewHolder.ResultFragmentViewHolder

class ResultFragmentPresenter(itemView: View) :
    SearchListingCallBack {
    private val context = itemView.context

    private val resultFragmentViewHolder = ResultFragmentViewHolder(itemView)
    private var resultRecyclerViewAdapter = ResultRecyclerViewAdapter(context)

    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        resultFragmentViewHolder.resultRecyclerView.layoutManager = linearLayoutManager
        resultRecyclerViewAdapter = ResultRecyclerViewAdapter(context)
        resultFragmentViewHolder.resultRecyclerView.adapter = resultRecyclerViewAdapter
    }

    override fun loadItemData(listingItemData: List<ListingItemData>) {
        resultRecyclerViewAdapter.setData(listingItemData)
        resultFragmentViewHolder.resultRecyclerView.startLayoutAnimation()
    }
}