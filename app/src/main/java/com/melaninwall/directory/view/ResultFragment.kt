package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.interfaces.ListItemListener
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ResultFragmentPresenter
import kotlinx.android.synthetic.main.result_fragment.*
import java.io.Serializable

class ResultFragment : NestedFragment(), ListItemListener {
    companion object {
        fun create(filteredList: List<ListingItemData>): ResultFragment {
            val bundle = Bundle()
            bundle.putSerializable(
                StorageKey.FILTERED_LIST.toString(),
                filteredList as Serializable
            )
            val fragment = ResultFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resultFragmentPresenter = ResultFragmentPresenter(view)
        val list = this.arguments?.getSerializable(StorageKey.FILTERED_LIST.toString())
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
            val listingFragment = ListingFragment.create(itemData)
            val navController = Navigation.findNavController(it)
            navController.navigate(
                R.id.action_resultFragment_to_listingFragment,
                listingFragment.arguments
            )
        }
    }
}