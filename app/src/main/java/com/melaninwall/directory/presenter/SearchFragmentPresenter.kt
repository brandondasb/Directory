package com.melaninwall.directory.presenter

import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.adapters.SearchRecyclerViewAdapter
import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.interfaces.QuerySearchCallback
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.viewHolder.SearchFragmentViewHolder

class SearchFragmentPresenter(itemView: View) :
    SearchListingCallBack,
    ListItemCategoryListener {

    private val context = itemView.context
    private val repo = Repo()
    private var textWatcher: TextWatcher? = null

    private val searchFragmentViewHolder = SearchFragmentViewHolder(itemView)

    private var searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context)

    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchFragmentViewHolder.searchRecyclerView.layoutManager = linearLayoutManager

        searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context)
        searchFragmentViewHolder.searchRecyclerView.adapter = searchRecyclerViewAdapter

    }

    override fun loadItemData(listingItemData: List<ListingItemData>) {
        searchRecyclerViewAdapter.setData(listingItemData)
        searchFragmentViewHolder.searchRecyclerView.startLayoutAnimation()
    }

    override fun launchCategoryFragment(
        selectedCategory: String
    ) {

    }

    fun setAdapter(listingItemData: QuerySearchCallback, stringQuery: String) {
        repo.getQueryData(listingItemData, stringQuery)
    }

    // TODO on textChange coming soon
    fun setAfterTextChangeListener() {
    }
}