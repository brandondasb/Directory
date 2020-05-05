package com.example.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.directory.R
import com.example.directory.model.ListingItemData
import kotlinx.android.synthetic.main.item_detail_fragment.*
import kotlinx.android.synthetic.main.list_item_home_card_child_recycler.*

class ListingFragment : Fragment() {

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
        val data = bundle?.getSerializable("listingItemData")
        data as ListingItemData

        fpaAboutTextView.text = data.about
        fpaNameTextView.text = data.name
        fpaCategoryTextView.text = data.category.toString()
        fpaAddress.text = data.address
        fpaCity.text = data.city
        fpaPostcode.text = data.postcode
        fpaWebsite.text = data.website
        fpatwitter.text = data.social.toString()
        fpaVerifiedTextView.text = data.verified.toString()
        Glide.with(this)
            .load(data.image)
            .fitCenter()
            .into(fpaImageView)

    }

}
