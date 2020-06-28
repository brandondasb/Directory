package com.melaninwall.directory.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melaninwall.directory.R
import com.melaninwall.directory.repo.Repo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.melaninwall.directory.interfaces.AuthenticationHandler
import com.melaninwall.directory.interfaces.SignupAuthorisation
import com.melaninwall.directory.repo.AuthEmailPassword
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    lateinit var signupAuthorisation: SignupAuthorisation
    private val repo = Repo()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupAuthorisation = AuthEmailPassword()
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
        } else if (password.length < 6) {
            Toast.makeText(
                this,
                "Password must be at least 6 characters long", Toast.LENGTH_SHORT
            ).show()
        }

        Log.d("###SIGNUP", "Email is : $email")
        Log.d("###SIGNUP", "password is : $password")

        signupAuthorisation.signup(email, password, getAuthHandler(username))

    }

    private fun getAuthHandler(username: String): AuthenticationHandler {
        return object : AuthenticationHandler {
            override fun onComplete(success: Boolean) {
                if (success) {
                    repo.saveUserToFireBaseDatabase(this@SignUpActivity, username)
                }
            }

            override fun onFailure(message: String?) {
                Log.d("###LOGIN", "failed to login because $message")
                Toast.makeText(this@SignUpActivity, "Something went wrong", Toast.LENGTH_SHORT)
            }
        }
    }
}