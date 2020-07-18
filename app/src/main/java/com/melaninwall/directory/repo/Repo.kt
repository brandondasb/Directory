package com.melaninwall.directory.repo

import android.content.Context
import android.content.Intent
import android.util.Log
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.DiscoverListingCallBack
import com.melaninwall.directory.model.*

class Repo {
    private val FIRESTORE = FirebaseFirestore.getInstance()
    private val BASE_COLLECTION = FIRESTORE.collection("listing")
    private val CATEGORY_COLLECTION = FIRESTORE.collection("category")
    private val HOME_CONTAINER_COLLECTION = FIRESTORE.collection("home_container")
    private val USER_COLLECTION = FIRESTORE.collection("/users/")
    private val BASE_DOCUMENT = FIRESTORE.document("")
    //ref to storage service for file
    private val STORAGE = FirebaseStorage.getInstance()
    private val storageRef = STORAGE.reference
    private val imagesRef = storageRef.child("images")
    var rootRef: StorageReference = storageRef.root // point to root folder
    var rootParent: StorageReference? = imagesRef.parent // point to parent of image folder

    fun getAllListing(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION.get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###ALLLISTING", " ${collection.query}->${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(ListingItemData::class.java)

                    homeListingCallback.loadAllGroupItemdata(
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

    fun getHomeData(discoverListingCallback: DiscoverListingCallBack) {
        BASE_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###OMG", "${document.data}-> ${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(ListingItemData::class.java)

                    discoverListingCallback.loadItemData(collection)
                } else {
                    Log.d("###", "Null, can't find any documents in collection => $collection")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)
            }
    }
    fun getCategoryData(categoryListingCallBack: CategoryListingCallBack) {
        CATEGORY_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###OMG", "${document.data}-> ${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(Category::class.java)

                    categoryListingCallBack.loadItemDataCategory(collection)
                } else {
                    Log.d("###", "Null, can't find any documents in collection => $collection")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETCATEGORY", "Error getting documents.", exception)
            }
    }

    fun getListingPerCity(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION.whereEqualTo("city", "london")

            .get()
            .addOnSuccessListener { collection ->

                if (collection != null) {
                    var collection = collection.toObjects(ListingItemData::class.java)
                    homeListingCallback.loadAllGroupItemdata(
                        listOf(
                            ItemGroup(
                                "London",
                                collection
                            )
                        )
                    )

                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)

            }
    }

    fun recentlyAdded(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION.orderBy("dateAdded")

            .get()
            .addOnSuccessListener { collection ->

                if (collection != null) {
                    var collection = collection.toObjects(ListingItemData::class.java)
                    homeListingCallback.loadAllGroupItemdata(
                        listOf(
                            ItemGroup(
                                "RecentlyAdded",
                                collection
                            )
                        )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETLISTING", "Error getting documents.", exception)

            }


    }

    /*example on how to make changes write to server*/
    fun addListing() {
        // Create a new user with a first and last name
        val listing = ListingItemData(
            1, "test the one com", "Ab",
            "https://firebasestorage.googleapis.com/v0/b/directory-mila.appspot.com/o/images%2Ftestbusiness.jpg?alt=media&token=af43bceb-a63f-42ea-9715-33233bb308e4",
            "image.co.uk",
            listOf("food", "gym"), false, "www.test.co.uk", "16 road", "manchester", "m2o5e",
            listOf(Social("twitter", "www.twitter/thetest"))
        )
// Add a new document with a generated ID
        FIRESTORE.collection("listing")
            .document("sampleDoc3")
            .set(listing) // .add(sampleData)
            .addOnSuccessListener { documentReference ->
                Log.d("###cmonson", "DocumentSnapshot added with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w("###cmoson", "Error adding document", e)
            }
    }

    fun saveUserToFireBaseDatabase(context: Context, username: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = USER_COLLECTION.document(uid)
        val user = User(uid, username, "")
        ref.set(user)
            .addOnSuccessListener {
                Log.d("###SAVEUSER", "USER stored in db")
                val intent = Intent(context, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)// clear stack
                context.startActivity(intent)
            }.addOnFailureListener {
                Log.d("###SAVEUSER", " user not saved: ${it.message} ")
            }
    }


}



