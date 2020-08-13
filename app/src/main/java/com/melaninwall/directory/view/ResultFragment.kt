package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey

import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ResultFragmentPresenter
import java.io.Serializable

class ResultFragment : Fragment() {
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
        val resultFragmentPresenter = ResultFragmentPresenter(view, fragmentManager)
        val list =  this.arguments?.getSerializable(StorageKey.FILTERED_LIST.toString())
        val filteredList = list as List<ListingItemData>?
        filteredList.let { list ->
            if (list != null)
                resultFragmentPresenter.loadItemData(list)
        }
    }
}