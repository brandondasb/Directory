package com.melaninwall.directory.presenter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.melaninwall.directory.R
import com.melaninwall.directory.R.layout.item_detail_page_about
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.PagerNavState
import com.melaninwall.directory.view.ListingFragment
import com.melaninwall.directory.viewHolder.ListingFragmentViewHolder

class ListingFragmentPresenter(itemView: View) {
    val view = itemView
    val viewHolder = ListingFragmentViewHolder(view)

    private lateinit var adapter: ListingPagerAdapter

    fun loadUi(listingItemData: ListingItemData?) {
        val fragment = ListingFragment.create(listingItemData)
        val data = fragment.arguments?.getSerializable(StorageKey.LISTING_ITEM_DATA.toString())
        data as ListingItemData

        viewHolder.name.text = data.name
//        viewHolder.about.text = data.about
        viewHolder.category.text = data.category.joinToString(" | ")
//        viewHolder.address.text = data.address
//        viewHolder.city.text = data.city
//        viewHolder.postcode.text = data.postcode
//        viewHolder.website.text = data.website
//        viewHolder.twitter.text = data.social.firstOrNull { it.name == "twitter" }?.url
//        viewHolder.facebook.text = data.social.firstOrNull { it.name == "facebook" }?.url
//        viewHolder.instagram.text = data.social.firstOrNull { it.name == "instagram" }?.url

        if (data.verified) {
            viewHolder.verified.visibility = View.VISIBLE
        } else {
            viewHolder.verified.visibility = View.GONE
        }
        Glide.with(view.context)
            .load(data.image)
            .centerCrop()
            .into(viewHolder.image)

        val aboutPage =
            LayoutInflater.from(view.context).inflate(item_detail_page_about, null)
        val galleryPage =
            LayoutInflater.from(view.context).inflate(R.layout.item_detail_page_gallery, null)
        val socialPage =
            LayoutInflater.from(view.context).inflate(R.layout.item_detail_page_social, null)
        val reviewPage =
            LayoutInflater.from(view.context).inflate(R.layout.item_detail_page_review, null)

        val titles = listOf(
            PagerNavState.ABOUT, PagerNavState.GALLERY, PagerNavState.CONTACT, PagerNavState.REVIEW
        )
        val pages: List<View> = listOf(aboutPage, galleryPage, socialPage, reviewPage)
        adapter = ListingPagerAdapter(titles, pages)
        viewHolder.viewPager.adapter = adapter

        TabLayoutMediator(viewHolder.tabLayout, viewHolder.viewPager) { tab, position ->
            tab.text = titles[position].name
        }.attach()

        viewHolder.viewPager.clipChildren
    }

    //TODO pager set up
    class ListingPagerAdapter(
        val title: List<PagerNavState>, val pages: List<View>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val container = LinearLayout(parent.context).apply {
                addView(pages[viewType])
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            return ComponentViewHolder(container)
//            return when (viewType) {
//                0 -> AboutPageViewHolder(pages[viewType])
//                1 -> GalleryPageViewHolder(pages[viewType])
//                2 -> SocialPageViewHolder(pages[viewType])
//                else -> throw RuntimeException()
//            }
        }

        override fun getItemCount(): Int = pages.size
        override fun getItemViewType(position: Int): Int = position

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            //do nothing
        }

        private class ComponentViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }
}