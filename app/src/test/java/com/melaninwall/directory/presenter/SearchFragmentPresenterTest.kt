package com.melaninwall.directory.presenter

import com.melaninwall.directory.interfaces.SearchFragmentView
import com.melaninwall.directory.model.ListingItemData
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchFragmentPresenterTest {

    @Mock
    private lateinit var searchFragmentView: SearchFragmentView

    private lateinit var searchFragmentPresenter: SearchFragmentPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        searchFragmentPresenter = SearchFragmentPresenter(searchFragmentView)
    }

    @Test
    fun `when loadItemData is called, then data is set`() {
        val list = listOf(ListingItemData(1, "test"))
        searchFragmentPresenter.loadItemData(list)
        verify(searchFragmentView).displayView(list)
    }
}