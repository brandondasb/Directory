package com.melaninwall.directory.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.melaninwall.directory.R
import com.melaninwall.directory.StorageKey
import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.presenter.ListingFragmentPresenter
import com.melaninwall.directory.view.tabLayout.Tab1Fragment
import com.melaninwall.directory.view.tabLayout.Tab2Fragment
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

    private lateinit var listingPagerAdapter: ListingPagerAdapter
    private lateinit var viewPager: ViewPager
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.item_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = detailsTablayout
        viewPager = view.findViewById(R.id.itemDetailPager)
        //TODO Pager test
        val arrayList = listOf(
            "About",
            "Gallery", "Contact", "reviews"
        )


        listingPagerAdapter = ListingPagerAdapter(childFragmentManager)
        //ini main fragment
        listingPagerAdapter.addFragment(Tab1Fragment(), arrayList[0])
        listingPagerAdapter.addFragment(Tab2Fragment(), arrayList[1])
        listingPagerAdapter.addFragment(Tab2Fragment(), arrayList[2])
        listingPagerAdapter.addFragment(Tab2Fragment(), arrayList[3])

        viewPager.adapter = listingPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

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

class ListingPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    val arrayList = arrayListOf<String>()
    val fragmentList = arrayListOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        arrayList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return arrayList[position]
    }

    override fun getCount(): Int = fragmentList.size

}
