package com.melaninwall.directory.interfaces

interface LoginAuthorisation {
    fun login(email: String, password: String, authenticationHandler: AuthenticationHandler)

}