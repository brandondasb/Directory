package com.melaninwall.directory.interfaces

interface SignupAuthorisation {
    fun signup(email: String, password: String, authenticationHandler: AuthenticationHandler)
}