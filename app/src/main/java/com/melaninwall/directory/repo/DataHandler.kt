package com.melaninwall.directory.repo

interface DataHandler {
    fun onComplete(success:Boolean)
    fun onFailure(message: String?)
}