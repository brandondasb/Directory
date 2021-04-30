package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.adapters.ResultRecyclerViewAdapter
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.interfaces.ResultFragmentView
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ResultFragmentPresenter
import com.melaninwall.directory.viewHolder.ResultFragmentViewHolder
import kotlinx.android.synthetic.main.result_fragment.*

class ResultFragment : NestedFragment(), ResultFragmentView, ListItemListener {
    private lateinit var resultFragmentViewHolder: ResultFragmentViewHolder
    private lateinit var resultRecyclerViewAdapter: ResultRecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resultFragmentPresenter = ResultFragmentPresenter(this)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        resultFragmentViewHolder = ResultFragmentViewHolder(view)
        resultFragmentViewHolder.resultRecyclerView.layoutManager = linearLayoutManager
        resultRecyclerViewAdapter = ResultRecyclerViewAdapter(view.context)
        resultFragmentViewHolder.resultRecyclerView.adapter = resultRecyclerViewAdapter

        val list = arguments?.getSerializable(StorageKey.FILTERED_LIST.toString())
        val filteredList = list as List<ListingItemData>?
        if (filteredList.isNullOrEmpty()) {
            resultFragmentResultsTextview.visibility = View.VISIBLE
        } else {
            resultFragmentResultsTextview.visibility = View.GONE
        }
        filteredList?.let { resultFragmentPresenter.loadItemData(it) }


    }

    override fun launchFragment(clickableView: View, itemData: ListingItemData) {
        clickableView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(StorageKey.LISTING_ITEM_DATA.toString(), itemData)
            arguments = bundle
            val navController = Navigation.findNavController(it)
            navController.navigate(
                R.id.action_resultFragment_to_listingFragment,
                arguments
            )
        }
    }

    override fun displayView(listingItemDataList: List<ListingItemData>) {
        resultRecyclerViewAdapter.setData(listingItemDataList)
        resultFragmentViewHolder.resultRecyclerView.startLayoutAnimation()
    }
}