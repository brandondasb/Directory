package com.melaninwall.directory.repo


import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot

//TODO attempt to create a build pattern
class ListBuilder(
    var collection: CollectionReference?,
    var limit: Long?
) {

    private constructor(builder: Builder) : this(builder.collection, builder.limit)

    class Builder {
        var collection: CollectionReference? = null
            private set
        var limit: Long? = 0
            private set

        fun collection(collection: CollectionReference) = apply { this.collection = collection }
        fun limit(limit: Long?) = apply { this.limit = limit }

        fun build(): CollectionReference? {
            return ListBuilder(this).collection
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