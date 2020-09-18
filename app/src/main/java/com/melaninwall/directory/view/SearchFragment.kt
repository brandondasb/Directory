package com.melaninwall.directory.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.SearchFragmentPresenter
import com.melaninwall.directory.repo.Repo
import java.io.Serializable


class SearchFragment : Fragment() {
    private val queryTextListener: SearchView.OnQueryTextListener? = null

    companion object {
        fun create(filteredList: List<ListingItemData>): SearchFragment {
            val bundle = Bundle()
            bundle.putSerializable(
                StorageKey.FILTERED_LIST.toString(),
                filteredList as Serializable
            )
            val fragment = SearchFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val listingRepo = Repo()
        val searchFragmentPresenter = SearchFragmentPresenter(view, fragmentManager)

        val searchListingCallback: SearchListingCallBack = object : SearchListingCallBack {
            override fun loadItemData(listingItemData: List<ListingItemData>) {
                searchFragmentPresenter.loadItemData(listingItemData)
            }
        }
        listingRepo.getSearchData(searchListingCallback)

        val categoryListingCallBack: CategoryListingCallBack = object : CategoryListingCallBack {
            override fun loadItemDataCategory(
                categoryItemData: List<Category>
            ) {
                searchFragmentPresenter.loadItemDataCategory(categoryItemData)
            }
        }
        listingRepo.getCategoryListing(categoryListingCallBack)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search_view, menu)

        (activity as MainActivity?)?.supportActionBar?.setDisplayShowTitleEnabled(false)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView


        searchView.apply {
            setIconifiedByDefault(false)
            maxWidth = Integer.MAX_VALUE
            this.queryHint
            setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}