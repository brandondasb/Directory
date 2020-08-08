package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ListingFragmentPresenter
import kotlinx.android.synthetic.main.user_profile_fragment.*

class ListingFragment : Fragment() {
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


        return inflater.inflate(R.layout.item_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        var data = bundle?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
            data as ListingItemData
        if (data == null) {
            //
        } else {
            ListingFragmentPresenter(view).loadUi(data)
        }
    }
}
