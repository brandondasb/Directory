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
    val services: List<String> = emptyList(),
    val phoneNumber: Int = -1,
    val openingHours: List<OpeningHours> = emptyList()
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