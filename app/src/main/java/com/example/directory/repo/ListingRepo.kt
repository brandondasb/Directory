package com.example.directory.repo

import android.util.Log
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.ItemGroup
import com.example.directory.model.ListingItemData
import com.google.firebase.firestore.FirebaseFirestore


class ListingRepo() {
    val DB = FirebaseFirestore.getInstance()
    private val BASE_COLLECTION = DB.collection("listing")
    private val HOME_CONTAINER_COLLECTION = DB.collection("home_container")
    private val BASE_DOCUMENT = DB.document("")


    fun getAllListing(listingCallback: ListingCallback) {
        BASE_COLLECTION.get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###OIII", " ${collection.query}->${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(ListingItemData::class.java)

                    listingCallback.loadAllGroupItemdata(
                        listOf(
                            ItemGroup(
                                "Header title", collection
                            ), ItemGroup(
                                "Heading2", collection

                            ), ItemGroup(
                                "Heading 3", collection

                            ), ItemGroup(
                                "heading 4", collection
                            )
                        )
                    )

                } else {
                    Log.d("###", "Null, can't find any documents in collection => $collection")
                }


            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)
            }

    }

    fun getHomeData(listingCallback: ListingCallback) {
        HOME_CONTAINER_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###OMG", "${document.data}-> ${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(ItemGroup::class.java)

                    listingCallback.loadAllGroupItemdata(collection)

                } else {
                    Log.d("###", "Null, can't find any documents in collection => $collection")
                }


            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)
            }

    }

    fun getListingPerCity(listingCallback: ListingCallback) {
        BASE_COLLECTION.whereEqualTo("city", "london")

            .get()
            .addOnSuccessListener { collection ->

                if (collection != null) {
                    var collection = collection.toObjects(ListingItemData::class.java)
                    listingCallback.loadLondon(listOf(ItemGroup("London", collection)))

                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)

            }
    }

    fun recentlyAdded(listingCallback: ListingCallback) {
        BASE_COLLECTION.orderBy("dateAdded")

            .get()
            .addOnSuccessListener { collection ->

                if (collection != null) {
                    var collection = collection.toObjects(ListingItemData::class.java)
                    listingCallback.loadLondon(listOf(ItemGroup("RecentlyAdded", collection)))

                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)

            }


    }


    /*example on how to make changes write to server*/
    fun addListing() {
        // Create a new user with a first and last name
        val listing = hashMapOf(
            "about" to "don",
            "city" to "london",
            "name" to "test",
            "postcode" to "lnaod2d",
            "category" to "food"
        )
// Add a new document with a generated ID
        DB.collection("listing")
            .add(listing)
            .addOnSuccessListener { documentReference ->
                Log.d("###DATA", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("###NODATA", "Error adding document", e)
            }
    }

    /*example on how to make changes write to server*/
    fun addHomeContainerListing() {

        val sampleData = ListingItemData(
            1, "test", "about", "image.co.uk", "image.co.uk",
            listOf("food", "gym"), false, "www.abc.co.uk", "3 road", "city", "md4o5e",
            listOf()
        )
// Add a new document with a generated ID
        DB.collection("home_container/near_me_list/near_me_coll/")
            .document("sampleDoc1")
            .set(sampleData)// using set to choose the doc ID with .document
            // .add(sampleData)
            .addOnSuccessListener { documentReference ->
                Log.d("###DATA", "DocumentSnapshot added with ID: ${documentReference}")
            }
            .addOnFailureListener { e ->
                Log.w("###NODATA", "Error adding document", e)
            }
    }


}



