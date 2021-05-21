package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.adapters.CategoryRecyclerViewAdapter
import com.melaninwall.directory.adapters.ListingSectionAdapter
import com.melaninwall.directory.interfaces.*
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.presenter.HomeFragmentPresenter
import com.melaninwall.directory.repo.HomeListRequest
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.viewHolder.HomeFragmentViewHolder
import java.io.Serializable

class HomeFragment : Fragment(), ListItemListener, HomeListingCallback, CategoryListingCallBack,
    HomeFragmentView {

    private lateinit var homeFragmentViewHolder: HomeFragmentViewHolder
    private lateinit var categoryRecyclerViewAdapter: CategoryRecyclerViewAdapter
    private lateinit var listingSectionAdapter: ListingSectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listingRepo = Repo()
        val context = requireContext()

        val horizontalLinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        homeFragmentViewHolder = HomeFragmentViewHolder(view)
        homeFragmentViewHolder.categoryRecyclerView.layoutManager = horizontalLinearLayoutManager
        categoryRecyclerViewAdapter = CategoryRecyclerViewAdapter(context)
        homeFragmentViewHolder.categoryRecyclerView.adapter = categoryRecyclerViewAdapter
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()

        homeFragmentViewHolder.homeRecyclerView.layoutManager = linearLayoutManager
        listingSectionAdapter = ListingSectionAdapter(context)
        homeFragmentViewHolder.homeRecyclerView.adapter = listingSectionAdapter

        val homeFragmentPresenter = HomeFragmentPresenter(this)
        val homeListingCallback: HomeListingCallback = object : HomeListingCallback {
            override fun loadSectionList(listingSectionData: List<Section>) {
                homeFragmentPresenter.loadSectionList(listingSectionData)
            }
        }
        val homeListRequest =
            HomeListRequest.Builder(homeListingCallback).recent().nearMe().hundred().build()
        listingRepo.buildHomeScreen(homeListRequest)

        val categoryListingCallBack: CategoryListingCallBack = object : CategoryListingCallBack {
            override fun loadCategoryList(categoryList: List<Category>) {
                homeFragmentPresenter.loadCategoryList(categoryList)
            }
        }
        listingRepo.getCategoryListing(categoryListingCallBack)
        //  listingRepo.addListing()//TODO  used for testing purposes, helps create sample data.
    }

    fun launchCategoryNavigator(clickableView: View, selectedCategory: String) {
        clickableView.setOnClickListener { category ->
            val repo = Repo()
            val callBack = object : SearchListingCallBack {
                override fun loadItemData(listingItemData: List<ListingItemData>) {
                    val bundle = Bundle()
                    bundle.putSerializable(
                        StorageKey.FILTERED_LIST.toString(),
                        listingItemData as Serializable
                    )
                    bundle.also { arguments = it }
                    val navController = Navigation.findNavController(category)
                    navController.navigate(
                        R.id.action_homeFragment_to_resultFragment,
                        arguments
                    )
                }
            }
            repo.getListingPerCategory(callBack, selectedCategory)
        }
    }

    override fun launchFragment(clickableView: View, itemData: ListingItemData) {
        clickableView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(StorageKey.LISTING_ITEM_DATA.toString(), itemData)
            arguments = bundle

            val navController = Navigation.findNavController(it)
            navController.navigate(
                R.id.action_homeFragment_to_listingFragment,
                arguments
            )
        }
    }

    override fun loadSectionList(listingSectionData: List<Section>) {
        listingSectionAdapter.setData(listingSectionData)
        homeFragmentViewHolder.homeRecyclerView.startLayoutAnimation()
    }

    override fun loadCategoryList(categoryList: List<Category>) {
        categoryRecyclerViewAdapter.setData(categoryList)
        homeFragmentViewHolder.categoryRecyclerView.startLayoutAnimation()
    }

    override fun displayViews(listingSectionData: List<Section>) {
        loadSectionList(listingSectionData)

    }

    override fun displayCategory(categoryList: List<Category>) {
        loadCategoryList(categoryList)
    }

    override fun showErrorWith(errorMessage: String) {
        TODO("Not yet implemented")
    }
}