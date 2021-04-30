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
    fun `when loadUi is called, with empty imageUrl then return default no image URL`() {
        val list = ListingItemData(image = "")
        listingFragmentPresenter.loadUi(list)
        verify(listingItemView).displayViews("https://upload.wikimedia.org/wikipedia/commons/6/6c/No_image_3x4.svg")
    }

    @Test
    fun `when load Ui is called, with  image url then pass URL to displayView`() {
        val list = ListingItemData(image = "www.google.co.uk/image")
        listingFragmentPresenter.loadUi(list)
        verify(listingItemView).displayViews("www.google.co.uk/image")
    }
}