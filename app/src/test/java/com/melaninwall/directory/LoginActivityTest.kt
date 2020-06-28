package com.melaninwall.directory

import com.google.firebase.auth.FirebaseAuth
import com.melaninwall.directory.view.LoginActivity
import org.junit.Assert.*
import org.junit.Test

class LoginActivityTest {

    @Test
    fun `when performSignIn is called on null, then error return`() {
        FirebaseAuth.getInstance().signInWithEmailAndPassword("","")

    }
}

