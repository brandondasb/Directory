package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.presenter.HomeFragmentPresenter
import com.melaninwall.directory.repo.HomeListRequest
import com.melaninwall.directory.repo.Repo
import java.io.Serializable

class HomeFragment : Fragment(), ListItemListener {

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
        val homeFragmentPresenter = HomeFragmentPresenter(view)
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
}