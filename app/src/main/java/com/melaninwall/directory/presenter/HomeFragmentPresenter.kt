package com.melaninwall.directory.presenter;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.ListingItemGroupAdapter
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.interfaces.ListItemCategoryListener
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ItemGroup
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View, private var fragmentManager: FragmentManager?) :
    HomeListingCallback, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener {

    private val context = itemView.context

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
    private var listingItemGroupAdapter = ListingItemGroupAdapter(context, this)

    init {
        //configureSpinner()
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        homeFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager

        listingItemGroupAdapter = ListingItemGroupAdapter(context, this)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingItemGroupAdapter

        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
        homeFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
    }

    override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {

        listingItemGroupAdapter.setData(listingItemGroupData)
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

    override fun launchCategoryFragment(itemData: Category) {
        //To change body of created functions use File | Settings | File Templates.
    }


}


