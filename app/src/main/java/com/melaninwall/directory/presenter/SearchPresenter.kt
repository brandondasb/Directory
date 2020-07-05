package com.melaninwall.directory.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.SearchRecyclerViewAdapter
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.view.SearchFragment
import com.melaninwall.directory.viewHolder.CategoryViewHolder
import com.melaninwall.directory.viewHolder.SearchFragmentViewHolder

class SearchPresenter(itemView: View, var fragmentManager: FragmentManager?) :
    SearchListingCallBack, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener {

    private val context = itemView.context

    private val searchFragmentViewHolder = SearchFragmentViewHolder(itemView)

    private var searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context, this)
    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)


    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchFragmentViewHolder.searchRecyclerView.layoutManager = linearLayoutManager

        searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.searchRecyclerView.adapter = searchRecyclerViewAdapter

        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
    }


    override fun loadItemData(listingItemData: List<ListingItemData>) {
        searchRecyclerViewAdapter.setData(listingItemData)
    }

    override fun loadItemDataCategory(listingItemData: List<Category>) {
        categoryRecyclerViewAdapter.setData(listingItemData)
    }

    override fun launchFragment(itemData: ListingItemData) {
        val bundle = Bundle()
        bundle.putSerializable("listingItemData", itemData)
        val listingFragment = ListingFragment()
        listingFragment.arguments = bundle

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

    override fun launchCategoryFragment(itemDataCategory: Category) {
        val bundle = Bundle()
        bundle.putSerializable("listingItemData", itemDataCategory)
        val categoryFilteredSearchFragment = SearchFragment()
        categoryFilteredSearchFragment.arguments = bundle

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, categoryFilteredSearchFragment)
            ?.addToBackStack(SearchFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

}