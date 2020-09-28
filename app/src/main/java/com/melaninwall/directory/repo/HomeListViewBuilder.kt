package com.melaninwall.directory.repo


import com.melaninwall.directory.model.ItemGroup
import com.melaninwall.directory.model.ListingItemData

//TODO attempt to create a builder pattern
class HomeListViewBuilder(
    val recent: MutableList<ListingItemData>?,
    val nearMe: MutableList<ListingItemData>?,
    val hundred: MutableList<ListingItemData>?
) {

    private constructor(builder: Builder) : this(builder.recent, builder.nearMe, builder.hundred)

    class Builder {
        var recent: MutableList<ListingItemData>? = null
            private set
        var nearMe: MutableList<ListingItemData>? = null
            private set
        var hundred: MutableList<ListingItemData>? = null
            private set

        fun recent(recent: MutableList<ListingItemData>?) = apply { this.recent = recent }
        fun nearMe(nearMe: MutableList<ListingItemData>?) = apply { this.nearMe = nearMe }
        fun hundred(hundred: MutableList<ListingItemData>?) = apply { this.hundred = hundred }


        fun build(): List<ItemGroup> {
            val recentList = this.recent?.let { ItemGroup("recently added", it) }
            val nearmeList = this.nearMe?.let { ItemGroup("near me", it) }
            val hundredList = this.hundred?.let { ItemGroup("top 100", it) }

//            val homeListingCallback = object : HomeListingCallback {
//                override fun loadAllGroupItemdata(listingItemGroupData: List<ItemGroup>) {
//                    listOf(
//                        recentList, nearmeList, hundredList
//                    ) as List<ItemGroup>
//                }
//
//            }
//            homeListingCallback.loadAllGroupItemdata(
//                listingItemGroupData = listOf(
//                    recentList, nearmeList, hundredList
//                ) as List<ItemGroup>
//            )

           // HomeListViewBuilder(this)
            return listOf(
                recentList, nearmeList, hundredList
            ) as List<ItemGroup>
        }

    }
}

//// version2
//data class CollectionData(
//    var collection: CollectionReference,
//    var limit: Long? = null
//) {
//fun build():CollectionReference{
//return CollectionData().collection
//}
//
//}