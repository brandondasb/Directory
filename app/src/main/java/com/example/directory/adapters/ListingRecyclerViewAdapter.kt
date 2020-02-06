package com.example.directory.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.directory.R
import com.example.directory.model.ListingItemData
import com.example.directory.model.Social
import com.example.directory.viewHolder.ListingViewHolder

class ListingRecyclerViewAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listingData: List<ListingItemData>

    private var dummyData = listOf(
        ListingItemData(
            1,
            "test1",
            "",
            "image.co.uk",
            "logo.co.uk",
            "food",
            true,
            "nope.co.uk",
            "",
            "m9",
            listOf(Social("", ""))
        ),
        ListingItemData(
            2,
            "test2",
            "about test well",
            "image.co.uk",
            "logo.co.uk",
            "food",
            false,
            "nonyabusiness.co.uk",
            "m8",
            "m9",
            listOf(Social("", ""))
        )
    )
    //listingData

    fun setData(listingData: List<ListingItemData>) {
        this.listingData = dummyData
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.list_item_home_card, viewGroup, false)
        return ListingViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val listingViewHolder: ListingViewHolder = viewHolder as ListingViewHolder
        val listing: ListingItemData = dummyData[position]

        val name: String = listing.name
        val about: String = listing.about
        val postcode: String = listing.postcode
        //  val image: Drawable = listing.image
        val category: String = listing.category
        listingViewHolder.name.text = name
        listingViewHolder.category.text = category
        listingViewHolder.postcode.text = postcode
        // listingViewHolder.image.drawable = image
    }

    override fun getItemCount(): Int {
        return listingData.size
    }


}