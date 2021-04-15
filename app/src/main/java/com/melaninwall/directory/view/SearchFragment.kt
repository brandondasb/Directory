package com.melaninwall.directory.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.adapters.SearchRecyclerViewAdapter
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.SearchFragmentView
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.SearchFragmentPresenter
import com.melaninwall.directory.repo.Repo
import com.melaninwall.directory.viewHolder.SearchFragmentViewHolder

class SearchFragment : Fragment(), ListItemListener, SearchFragmentView {

    private val queryTextListener: SearchView.OnQueryTextListener? = null
    private lateinit var searchFragmentViewHolder: SearchFragmentViewHolder
    private lateinit var searchRecyclerViewAdapter: SearchRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        val context = requireContext()
        searchFragmentViewHolder = SearchFragmentViewHolder(view)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchFragmentViewHolder.searchRecyclerView.layoutManager = linearLayoutManager

        searchRecyclerViewAdapter = SearchRecyclerViewAdapter(context)
        searchFragmentViewHolder.searchRecyclerView.adapter = searchRecyclerViewAdapter

        val listingRepo = Repo()
        val searchFragmentPresenter = SearchFragmentPresenter(this)

        val searchListingCallback: SearchListingCallBack = object : SearchListingCallBack {
            override fun loadItemData(listingItemData: List<ListingItemData>) {
                searchFragmentPresenter.loadItemData(listingItemData)
            }
        }
        listingRepo.getSearchData(searchListingCallback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search_view, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.apply {
            setIconifiedByDefault(false)
            maxWidth = Integer.MAX_VALUE
            this.queryHint
            setOnQueryTextListener(queryTextListener)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun launchFragment(clickableView: View, itemData: ListingItemData) {
        clickableView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(StorageKey.LISTING_ITEM_DATA.toString(), itemData)
            arguments = bundle

            val navController = Navigation.findNavController(it)
            navController.navigate(
                R.id.action_searchFragment_to_listingFragment,
                arguments
            )
        }
    }

    override fun displayView(listingItemData: List<ListingItemData>) {
        searchRecyclerViewAdapter.setData(listingItemData)
        searchFragmentViewHolder.searchRecyclerView.startLayoutAnimation()
    }
}