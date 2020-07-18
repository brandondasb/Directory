package com.melaninwall.directory.presenter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.DiscoverRecyclerViewAdapter
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.DiscoverListingCallBack
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.DiscoverFragmentViewHolder

class DiscoverPresenter(itemView: View, var fragmentManager: FragmentManager?) :
    DiscoverListingCallBack, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener {

    private val context = itemView.context

    private val searchFragmentViewHolder = DiscoverFragmentViewHolder(itemView)

    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
    private var searchRecyclerViewAdapter = DiscoverRecyclerViewAdapter(context, this)


    init {
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchFragmentViewHolder.searchRecyclerView.layoutManager = linearLayoutManager
        searchFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager

        searchRecyclerViewAdapter = DiscoverRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.searchRecyclerView.adapter = searchRecyclerViewAdapter

        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
        searchFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
    }


    override fun loadItemData(listingItemData: List<ListingItemData>) {
        searchRecyclerViewAdapter.setData(listingItemData)
    }

    override fun loadItemDataCategory(categoryItemData: List<Category>) {
        categoryRecyclerViewAdapter.setData(categoryItemData)
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

    }

}