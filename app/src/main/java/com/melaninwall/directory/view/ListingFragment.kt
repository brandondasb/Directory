package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ListingFragmentPresenter

class ListingFragment : Fragment(), ListingFragmentView {

    companion object {
        fun create(listing: ListingItemData?): ListingFragment {
            val bundle = Bundle()
            bundle.putSerializable(StorageKey.LISTING_ITEM_DATA.toString(), listing)
            val fragment = ListingFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        var data = bundle?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData
        ListingFragmentPresenter(view, this).loadUi(data)
    }

    override fun displayViews() {
        TODO("Not yet implemented")
    }
}
