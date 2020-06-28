package com.melaninwall.directory.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melaninwall.directory.R
import com.melaninwall.directory.interfaces.AuthenticationHandler
import com.melaninwall.directory.interfaces.LoginAuthorisation
import com.melaninwall.directory.repo.AuthEmailPassword
import kotlinx.android.synthetic.main.activity_log_in.*

class LoginActivity : AppCompatActivity() {

    lateinit var loginAuthorisation: LoginAuthorisation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginAuthorisation = AuthEmailPassword()
        setContentView(R.layout.activity_log_in)

        val email = email_login.text.toString()
        val password = password_log_in.text.toString()
        Log.d("###mainactivity", "email is :$email")
        Log.d("###mainactivity", "password is :$password")

        textView_sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        login_button.setOnClickListener {

            performSignIn()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    private fun performSignIn() {
        val email = email_login.text.toString()
        val password = password_log_in.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Log.d("###LOGIN", "please fill both email and password.")
            Toast.makeText(this, "please enter an email and a password.", Toast.LENGTH_SHORT).show()
            return
        }
        loginAuthorisation.login(email, password, getAuthHandler())
    }

    private fun getAuthHandler(): AuthenticationHandler {
        return object : AuthenticationHandler {
            override fun onComplete(success: Boolean) {
                if (success) {
                    Log.d("###", "Successfully logged in")
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)// clear stack
                    startActivity(intent)
                }
            }
            override fun onFailure(message: String?) {
                Log.d("###LOGIN", "failed to login because $message")
                Toast.makeText(this@LoginActivity, "Something went wrong", Toast.LENGTH_SHORT)}

        }
    }
}
