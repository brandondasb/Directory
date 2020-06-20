package com.melaninwall.directory.repo

import com.google.firebase.auth.FirebaseAuth
import com.melaninwall.directory.interfaces.AuthenticationHandler
import com.melaninwall.directory.interfaces.LoginAuthorisation
import com.melaninwall.directory.interfaces.SignupAuthorisation

class Auth : LoginAuthorisation, SignupAuthorisation {
    private val FIREBASE_AUTH = FirebaseAuth.getInstance()
    override fun login(
        email: String,
        password: String,
        authenticationHandler: AuthenticationHandler
    ) {
        FIREBASE_AUTH.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                authenticationHandler.onComplete(it.isSuccessful)
            }.addOnFailureListener {
                authenticationHandler.onFailure(it.message)
            }
    }

    override fun signup(
        email: String,
        password: String,
        authenticationHandler: AuthenticationHandler
    ) {
        FIREBASE_AUTH.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                authenticationHandler.onComplete(it.isSuccessful)
            }.addOnFailureListener {
                authenticationHandler.onFailure(it.message)
            }
    }
}