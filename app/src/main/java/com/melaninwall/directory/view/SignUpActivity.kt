package com.melaninwall.directory.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melaninwall.directory.R
import com.melaninwall.directory.repo.ListingRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val repo = ListingRepo()
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        register_button.setOnClickListener {
            performRegister()
        }
        already_have_account_text_sign_up.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        currentUI(currentUser)
    }

    private fun currentUI(user: FirebaseUser?) {
        if (user != null) {
            // TODO do something to update UI for a logged in user
        } else {
            //TODO do something else, not logged in should asked to log in or register
        }

    }

    private fun performRegister() {
        val email = email_register.text.toString()
        val password = password_register.text.toString()
        val username = username_register.text.toString()
        if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
            Toast.makeText(
                this,
                "Please  fill in all the details username, email and pw", Toast.LENGTH_SHORT
            ).show()
            return
        }
        Log.d("###SIGNUP", "Email is : $email")
        Log.d("###SIGNUP", "pasword is : $password")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) return@addOnCompleteListener
                // else if successful statement
                Log.d("###SIGNUP", "Successfully created user with uid, ${task.result?.user?.uid} ")

                repo.saveUserToFireBaseDatabase(this, username)
            }
            .addOnFailureListener { Log.d("###SIGNUP", "Failed to create user: ${it.message}")
            }
    }
}