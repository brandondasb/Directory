package com.melaninwall.directory.presenter

import com.melaninwall.directory.model.ListingItemData
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchFragmentPresenterTest {
    private lateinit var searchFragmentPresenter: SearchFragmentPresenter
    //   private val fragmentManager = FragmentManager()
    @Before
    fun setUp() {
        //   searchFragmentPresenter = SearchFragmentPresenter(View, FragmentManager)
    }

    @Test
    fun `when loadItemData is called, then data is set`() {
        val list = listOf<ListingItemData>(ListingItemData(1, "test"))
        searchFragmentPresenter.loadItemData(list)

        assertTrue(list.contains(ListingItemData(1, "11")))
    }
}