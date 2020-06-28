package com.melaninwall.directory.interfaces

import com.google.firebase.auth.FirebaseUser

interface LoginAuthorisation {
    fun login(email: String, password: String, authenticationHandler: AuthenticationHandler)
    fun updateUI(user: FirebaseUser?)

}