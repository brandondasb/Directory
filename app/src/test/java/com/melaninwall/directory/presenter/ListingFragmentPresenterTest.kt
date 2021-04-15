package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.ListingFragmentView
import com.melaninwall.directory.model.ListingItemData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ListingFragmentPresenterTest {
    @Mock
    private lateinit var listingItemView: ListingFragmentView
    private lateinit var listingFragmentPresenter: ListingFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        listingFragmentPresenter = ListingFragmentPresenter(listingItemView)
    }

    @Test
    fun `when loadUi is called, with empty imageUrl then return default text`() {
        val list = ListingItemData(image = "")
        listingFragmentPresenter.loadUi(list)
        verify(listingItemView).displayViews("No data found")
    }

    @Test
    fun `when load Ui is called, with  image url then pass URL to displayView`() {
        val list = ListingItemData(image = "www.google.co.uk/image")
        listingFragmentPresenter.loadUi(list)
        verify(listingItemView).displayViews("www.google.co.uk/image")
    }
}