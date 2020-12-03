package com.melaninwall.directory.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PagerSectionKtTest {

    @Test
    fun `when a correct type is passed, then PagerSection is return`() {
        val actual = lookUpPagerSection(0)
        val expected = PagerSection.SUMMARY
        assertTrue(actual.declaringClass == PagerSection::class.java)
    }

    @Test
    fun `when 0 type is passed, then first item in PagerSection is returned`() {
        val actual = lookUpPagerSection(0)
        val expected = PagerSection.values()[0]
        assertEquals(expected, actual)
    }

    @Test
    fun `when 1 type is passed, then second item in PagerSection is returned`() {
        val actual = lookUpPagerSection(1)
        val expected = PagerSection.values()[1]
        assertEquals(expected, actual)
    }

//    @Test
//    fun `when an incorrect type is passed, then a runtime error is thrown`() {
//        //TBC how assert a throws
//    }

}