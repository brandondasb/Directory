package com.example.directory.model

data class ListingItemData(
    val id: Int = -1,
    val name: String = "",
    val about: String = "",
    val image: String = "",
    val logo: String = "",
    val category: List<String> = emptyList(),//could be a list
    var verified: Boolean = false,
    val website: String = "",
    val address: String = "",
    val city: String = "",
    val postcode: String = "",
    val social: List<Social> = emptyList()
// TBC
)

data class Social(
    val name: String = "",
    val url: String = ""

)
