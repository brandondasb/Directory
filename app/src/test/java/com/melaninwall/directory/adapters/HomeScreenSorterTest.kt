package com.melaninwall.directory.adapters

import com.melaninwall.directory.model.ListingItemData
import com.melaninwall.directory.model.Section
import com.melaninwall.directory.repo.HomeScreenSection
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class HomeScreenSorterTest {
    private lateinit var homeScreenSorter: SectionSorter

    @Before
    fun setup() {
        homeScreenSorter = HomeScreenSectionSorter()
    }

    @Test
    fun `when we're passed an empty list, then return an empty list`() {
        val result = homeScreenSorter.sortList(emptyList()) {}

        assertTrue(result.isEmpty())
    }

    @Test
    fun `when list of one is passed, return the same list of one`() {
        val sectionList = listOf(
            Section(
                HomeScreenSection.HUNDRED,
                emptyList<ListingItemData>().toMutableList()
            )
        )
        val result = homeScreenSorter.sortList(
            sectionList
        ) {}


        assertEquals(sectionList, result)

    }

    @Test
    fun `when 2  provided lists are already filtered,then return them in the same order`() {
        val sectionList = listOf(
            Section(
                HomeScreenSection.HUNDRED,
                emptyList<ListingItemData>().toMutableList()
            ), Section(
                HomeScreenSection.NEARME,
                emptyList<ListingItemData>().toMutableList()
            )
        )

        val result = homeScreenSorter.sortList(
            sectionList
        ) {}
        assertEquals(sectionList, result)
    }

    @Test
    fun `when 2 lists are provided unordered, then return them in the correct order`() {
        val sectionList = listOf(
            Section(
                HomeScreenSection.NEARME,
                emptyList<ListingItemData>().toMutableList()
            ), Section(
                HomeScreenSection.HUNDRED,
                emptyList<ListingItemData>().toMutableList()
            )
        )

        val actual = homeScreenSorter.sortList(sectionList) { section: Section -> section.type }

        val expected = listOf(
            Section(
                HomeScreenSection.HUNDRED,
                emptyList<ListingItemData>().toMutableList()
            ), Section(
                HomeScreenSection.NEARME,
                emptyList<ListingItemData>().toMutableList()
            )
        )

        assertEquals(expected, actual)
    }

}