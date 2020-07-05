package com.melaninwall.directory.model

import java.io.Serializable

data class ListingItemData(
    val id: Int = -1,
    val name: String = "",
    val about: String = "",
    val image: String = "",
    val logo: String = "",
    val category: List<String> = emptyList(), //TODO can I Pass Category class directly
    var verified: Boolean = false,
    val website: String = "",
    val address: String = "",
    val city: String = "",
    val postcode: String = "",
    val social: List<Social> = emptyList()
// TBC
) : Serializable

data class Social(
    val name: String = "",
    val url: String = ""

) : Serializable

data class Category(
    val name: String = "",
    val url: String = ""
) : Serializable
