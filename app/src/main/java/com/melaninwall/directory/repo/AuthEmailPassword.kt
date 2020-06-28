package com.melaninwall.directory.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.melaninwall.directory.interfaces.AuthenticationHandler
import com.melaninwall.directory.interfaces.LoginAuthorisation
import com.melaninwall.directory.interfaces.SignupAuthorisation

class AuthEmailPassword : LoginAuthorisation, SignupAuthorisation {
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

    override fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //toDO user is signed in
        }else{
            //TODO  no user is signed in
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
                val throwable = it.cause!!
                if (throwable != null) {
                    //TODO throw something if email already exist
                }
            }
    }
}