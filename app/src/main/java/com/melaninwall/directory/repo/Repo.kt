package com.melaninwall.directory.repo

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import com.melaninwall.directory.interfaces.CategoryListingCallBack
import com.melaninwall.directory.interfaces.HomeListingCallback
import com.melaninwall.directory.interfaces.QuerySearchCallback
import com.melaninwall.directory.interfaces.SearchListingCallBack
import com.melaninwall.directory.model.*
import com.melaninwall.directory.view.MainActivity

class Repo {
    private val FIRESTORE = FirebaseFirestore.getInstance()
    private val BASE_COLLECTION = FIRESTORE.collection("listing")
    private val CATEGORY_COLLECTION = FIRESTORE.collection("category")
    private val USER_COLLECTION = FIRESTORE.collection("/users/")

    //ref to storage service for file
    private val STORAGE = FirebaseStorage.getInstance()
    private val storageRef = STORAGE.reference
    private val imagesRef = storageRef.child("images")

    private val sectionMap: Map<HomeScreenSection, (HomeListingCallback) -> Unit> = mapOf(
        HomeScreenSection.RECENT to ::getRecentlyAdded,
        HomeScreenSection.NEARME to ::getNearYou,
        HomeScreenSection.HUNDRED to ::getTopHundred
    )

    fun buildHomeScreen(homeListRequest: HomeListRequest) {
        val callback = homeListRequest.callback
        homeListRequest.section.forEach {
            sectionMap[it]?.invoke(callback)
        }
    }

    private fun getTopHundred(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###ALLLISTING", " ${collection.query} -> ${document.data.values}")
                        document.data
                    }
                    val collectionListingItem = mutableList(collection)

                    homeListingCallback.loadSectionList(
                        listOf(
                            Section(HomeScreenSection.HUNDRED, collectionListingItem)
                        )
                    )
                } else {
                    Log.d(
                        "###ALLLISTING",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###ALLLISTING", "Error getting documents.", exception)
            }
    }

    /* RETURN LIST OF NearYou ADDITION*/
    private fun getNearYou(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION
            .orderBy("dateAdded", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###RECENT", " ${collection.query} -> ${document.data.values}")
                        document.data
                    }
                    val collectionListingItem = mutableList(collection)
                    homeListingCallback.loadSectionList(
                        listOf(
                            Section(HomeScreenSection.NEARME, collectionListingItem)
                        )
                    )

                } else {
                    Log.d(
                        "###OHSHITRECENT",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###RECENT", "Error getting documents.", exception)
            }
    }

    fun getRecentlyAdded(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION
            .orderBy("dateAdded", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###RECENT", " ${collection.query} -> ${document.data.values}")
                        document.data
                    }
                    val collectionListingItem = mutableList(collection)
                    homeListingCallback.loadSectionList(
                        listOf(
                            Section(HomeScreenSection.RECENT, collectionListingItem)
                        )
                    )
                } else {
                    Log.d(
                        "###RECENT",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            ?.addOnFailureListener { exception ->
                Log.d("###ALLLISTING", "Error getting documents.", exception)
            }
    }

    /* RETURN LIST OF getMostLiked ADDITION WIP*/
    fun getMostLiked(homeListingCallback: HomeListingCallback) {
        BASE_COLLECTION
            .orderBy("dateAdded", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###RECENT", " ${collection.query} -> ${document.data.values}")
                        document.data
                    }
                    val collectionListingItem = mutableList(collection)
                } else {
                    Log.d(
                        "###RECENT",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            ?.addOnFailureListener { exception ->
                Log.d("###ALLLISTING", "Error getting documents.", exception)
            }
    }/* RETURN LIST OF LATEST ADDITION*/

    private fun mutableList(collection: QuerySnapshot): MutableList<ListingItemData> =
        collection.toObjects(ListingItemData::class.java)

    fun getSearchData(searchListingCallback: SearchListingCallBack) {

        BASE_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###SEARCHDATA", "${document.data}-> ${document.data.values}")
                        document.data
                    }
                    var collection = collection.toObjects(ListingItemData::class.java)

                    searchListingCallback.loadItemData(collection)
                } else {
                    Log.d(
                        "###SEARCHDATA",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###SEARCHDATA", "Error getting documents.", exception)
            }
    }

    fun getQueryData(querySearchCallback: QuerySearchCallback, searchText: String) {
        var nameListing = String
        //  \uf8ff this means end can have the search string with any character after it
        // BASE_COLLECTION.orderBy("name")
        //  .startAfter(searchText)
        //  .endAt("$searchText\uf8ff").get().addOnCompleteListener {  }

        BASE_COLLECTION.whereIn("name", listOf(nameListing))
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###GETQueryData", "${document.data}-> ${document.data.values}")
                    }
                    var filterListingList = collection.toObjects(ListingItemData::class.java)
                }
            }
    }

    fun getCategoryListing(categoryListingCallBack: CategoryListingCallBack) {
        CATEGORY_COLLECTION
            .get()
            .addOnSuccessListener { collection ->
                if (collection != null) {
                    for (document in collection) {
                        Log.d("###GETCATEGORY", "${document.data}-> ${document.data.values}")
                        document.data
                    }
                    val categoryCollection = collection.toObjects(Category::class.java)

                    categoryListingCallBack.loadCategoryList(categoryCollection)
                } else {
                    Log.d(
                        "###GETCATEGORY",
                        "Null, can't find any documents in collection => $collection"
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d("###GETCATEGORY", "Error getting documents.", exception)
            }
    }

    fun getListingPerCategory(
        searchListingCallBack: SearchListingCallBack,
        selectedCategory: String
    ) {
        BASE_COLLECTION.whereArrayContains("category", selectedCategory)
            .get()
            .addOnSuccessListener { collection ->

                if (collection != null) {
                    var collection = collection.toObjects(ListingItemData::class.java)
                    searchListingCallBack.loadItemData(collection)
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



