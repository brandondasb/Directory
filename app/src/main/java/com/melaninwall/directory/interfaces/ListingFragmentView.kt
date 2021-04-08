package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.ListingItemData

interface ListingFragmentView {
    //Call a presenter method every time there is user interaction  As the presenter must be view agnostic, it uses an interface that needs to be implemented
    fun displayViews(data: ListingItemData)
//  ideal look  fun displayViews(name:String,category:String,image:String,isVerified:Boolean)

}