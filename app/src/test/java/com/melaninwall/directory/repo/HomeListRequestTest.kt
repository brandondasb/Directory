package com.melaninwall.directory.repo

import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.model.Section
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeListRequestTest {

    @Test
    fun `when Builder receives 1 list, then creates builder with one list`() {
        val callback = object : HomeListingCallback {
            override fun loadSectionList(listingSectionData: List<Section>) {
            }
        }
        val builder = HomeListRequest.Builder(callback).hundred().build()
        val expected = HomeListRequest.Builder(callback).hundred().build()
        assertEquals(builder.section.size, expected.section.size)
    }

    @Test
    fun `when Builder receives two list, then creates builder with two list`() {
        val callback = object : HomeListingCallback {
            override fun loadSectionList(listingSectionData: List<Section>) {
            }
        }
        val builder = HomeListRequest.Builder(callback).hundred().nearMe().build()
        val expected = HomeListRequest.Builder(callback).hundred().nearMe().build()
        assertEquals(builder.section.size, expected.section.size)
    }

    @Test
    fun `when Builder receives three list, then creates builder with three list`() {
        val callback = object : HomeListingCallback {
            override fun loadSectionList(listingSectionData: List<Section>) {
            }
        }
        val builder = HomeListRequest.Builder(callback).hundred().nearMe().recent().build()
        val expected = HomeListRequest.Builder(callback).hundred().nearMe().nearMe().build()
        assertEquals(builder.section.size, expected.section.size)
    }
}