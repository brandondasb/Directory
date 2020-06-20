package com.melaninwall.directory.interfaces

interface AuthenticationHandler {
    fun onComplete(success:Boolean)
    fun onFailure(message: String?)
}