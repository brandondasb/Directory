package com.example.directory.repo

import android.util.Log
import com.example.directory.interfaces.ListingCallback
import com.example.directory.model.ListingItemData
import com.google.firebase.firestore.FirebaseFirestore


class ListingRepo() {
    val DB = FirebaseFirestore.getInstance()
    private val BASE_COLLECTION = DB.collection("listing")
    private val BASE_DOCUMENT = DB.document("")


    fun getAllListing(listingCallback: ListingCallback) {
        BASE_COLLECTION.get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###", "${document.data}")
                    }
                    var collection = collection.toObjects(ListingItemData::class.java)
                    listingCallback.loadAllListingItemData(collection)
//                    collection.forEach { document ->
//                        val listingItemData = document.toObject(ListingItemData::class.java)
//
//                     //   listingCallback.loadAllListingItemData(listingItemData)
//
//                    }
                } else {
                    Log.d("###", "Null, can't find any documents in collection =>$collection")
                }


            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)
            }

    }

    /*example on how to make changes write to server*/
    fun addTestUser() {
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "don",
            "last" to "lambo",
            "born" to 1815
        )
// Add a new document with a generated ID
        DB.collection("listing")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("###DATA", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("###NODATA", "Error adding document", e)
            }
    }


}



