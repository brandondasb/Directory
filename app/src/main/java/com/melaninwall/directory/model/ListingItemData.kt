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
    val social: List<Social> = emptyList(),
    val openingHours: List<OpeningHours> = emptyList()
//        listOf(
//        OpeningHours("Monday", 10, 30, 5, 0),
//        OpeningHours("Tuesday", 10, 20, 5, 0),
//        OpeningHours("Wednesday", 10, 20, 5, 0),
//        OpeningHours("Thursday", 10, 20, 5, 0),
//        OpeningHours("Friday", 10, 20, 5, 0),
//        OpeningHours("Saturday", 10, 20, 5, 0),
//        OpeningHours("Sunday", 10, 20, 5, 0)
//    )
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

data class OpeningHours(
    val dayOfWeek: String = "",
    val startHour: Int = 0,
    val startMinute: Int = 0,
    val endHour: Int = 0,
    val endMinute: Int = 0
) : Serializable