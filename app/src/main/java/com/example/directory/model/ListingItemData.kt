package com.example.directory.model

data class ListingItemData(
    val id: Int,
    val name: String,
    val about: String,
    val image: String,
    val logo: String,
    val category: String,//could be a list
    var verified: Boolean,
    val website: String,
    val address: String,
    val postcode: String,
    val social: List<Social>
// TBC
) {
    constructor() : this(-1,"","","","","",false,"","","", emptyList())

}

data class Social(
    val name: String,
    val url: String

)
