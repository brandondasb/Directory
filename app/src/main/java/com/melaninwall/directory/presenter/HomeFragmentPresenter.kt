package com.melaninwall.directory.presenter;

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.FragmentManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.ListingItemGroupAdapter
import com.melaninwall.directory.interfaces.*
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ItemGroup
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.view.SearchFragment
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.HomeFragmentViewHolder

class HomeFragmentPresenter(itemView: View, private var fragmentManager: FragmentManager?) :
    HomeListingCallback, CategoryListingCallBack,
    ListItemListener, ListItemCategoryListener, QuerySearchCallback {

    private val context = itemView.context
    private var textWatcher: TextWatcher? = null
    var nameList: ArrayList<String>? = null
    var categoryList: ArrayList<String>? = null
    var repo = Repo()

    private val homeFragmentViewHolder = HomeFragmentViewHolder(itemView)
    private var categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context, this)
    private var listingItemGroupAdapter = ListingItemGroupAdapter(context, this)

    init {
        homeFragmentViewHolder.homeSearchEditText.addTextChangedListener(textWatcher)

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

        val searchKeyword = homeFragmentViewHolder.homeSearchEditText.text.toString()

        homeFragmentViewHolder.homeSearchButton.setOnClickListener { searchButton ->
            firebaseListingSearch(searchKeyword)

        }
    }


    private fun firebaseListingSearch(keyWord: String) {
        //setAdapter(keyWord)


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

    override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {

        listingItemGroupAdapter.setData(listingItemGroupData)
    }

    override fun loadItemDataCategory(
        listingItemData: List<ListingItemData>,
        categoryItemData: List<Category>
    ) {
        categoryRecyclerViewAdapter.setData(listingItemData, categoryItemData)
    }

    override fun launchFragment(itemData: ListingItemData) {
        val listingFragment = ListingFragment.create(itemData)

        // lunch frag
        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(ListingFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

    override fun launchCategoryFragment(itemData: List<ListingItemData>, selectedCategory: String) {
        val filteredByCategoryList = itemData.filter { it.category.contains(selectedCategory) }
        val listingFragment = SearchFragment.create(filteredByCategoryList)

        fragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, listingFragment)
            ?.addToBackStack(SearchFragment::class.java.simpleName)
            ?.commitAllowingStateLoss()
    }

    // TODO on textChange coming soon
    fun setAfterTextChangeListener() {
        if (textWatcher != null) {
            homeFragmentViewHolder.homeSearchEditText.removeTextChangedListener(textWatcher)
        }

        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    //TODO  setAdapter(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

}

