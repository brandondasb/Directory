package com.melaninwall.directory.presenter;

import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.ListingSectionAdapter
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.interfaces.QuerySearchCallback
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View) :
    HomeListingCallback, CategoryListingCallBack, QuerySearchCallback {

    private val context = itemView.context
    private var textWatcher: TextWatcher? = null
    var nameList: ArrayList<String>? = null
    var categoryList: ArrayList<String>? = null
    var repo = Repo()

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context)
    private var listingItemGroupAdapter = ListingSectionAdapter(context)

    init {
        //configureSpinner()
        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        homeFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager
        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context)
        homeFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()

        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingItemGroupAdapter = ListingSectionAdapter(context)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingItemGroupAdapter
    }

    fun setAdapter(listingItemData: QuerySearchCallback, stringQuery: String) {
        repo.getQueryData(listingItemData, stringQuery)
    }

    override fun loadQueriedData(
        queriedListingItemData: List<ListingItemData>,
        searchText: String
    ) {
        TODO("not implemented") //An Adapter, with a setData function that takes list and search criteria
    }

    override fun loadCategoryList(categoryList: List<Category>) {
        categoryRecyclerViewAdapter.setData(categoryList)
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()
    }

    override fun loadSectionList(listingSectionData: List<Section>) {
        listingItemGroupAdapter.setData(listingSectionData)
        homeFragmentViewHolder.homeRecyclerView.startLayoutAnimation()
    }
}

