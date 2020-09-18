package com.melaninwall.directory.presenter

import android.text.TextWatcher
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.SearchRecyclerViewAdapter
import com.melaninwall.directory.interfaces.*
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.SearchFragmentViewHolder

class SearchFragmentPresenter(itemView: View, var fragmentManager: FragmentManager?) :
    SearchListingCallBack, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener {

    private val context = itemView.context
    private val repo = Repo()
    private var textWatcher: TextWatcher? = null

    private val searchFragmentViewHolder = SearchFragmentViewHolder(itemView)

    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
    private var searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context, this)

    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchFragmentViewHolder.searchRecyclerView.layoutManager = linearLayoutManager
        searchFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager

        searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.searchRecyclerView.adapter = searchRecyclerViewAdapter

        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
    }

    override fun loadItemData(listingItemData: List<ListingItemData>) {
        searchRecyclerViewAdapter.setData(listingItemData)
    }

    override fun loadItemDataCategory(
        categoryItemData: List<Category>
    ) {
        categoryRecyclerViewAdapter.setData(categoryItemData)
    }

    override fun launchFragment(itemData: ListingItemData) {
        val listingFragment = ListingFragment.create(itemData)

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

    override fun launchCategoryFragment(
        itemDataCategory: String
    ) {

    }

    fun setAdapter(listingItemData: QuerySearchCallback, stringQuery: String) {
        repo.getQueryData(listingItemData, stringQuery)
    }

    // TODO on textChange coming soon
    fun setAfterTextChangeListener() {
    }
}