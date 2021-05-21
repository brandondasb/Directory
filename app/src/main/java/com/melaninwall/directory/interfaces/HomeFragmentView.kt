package com.melaninwall.directory.interfaces

import com.melaninwall.directory.model.Category
import com.melaninwall.directory.model.Section


interface HomeFragmentView {
    //Call a presenter method every time there is user interaction  As the presenter must be view agnostic, it uses an interface that needs to be implemented
    fun displayViews(listingSectionData: List<Section>)
    fun showErrorWith(errorMessage: String)
    fun displayCategory(categoryList: List<Category>)
    /** may need to more function later like
     *  onLoadStatus
     * hideLoadStatus
     * showErrorListing
     * HideError
     * **/

}