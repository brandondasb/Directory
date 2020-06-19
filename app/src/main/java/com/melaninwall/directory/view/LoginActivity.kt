package com.melaninwall.directory.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.melaninwall.directory.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_log_in.*

class LoginActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val email = email_login.text.toString()
        val password = password_log_in.text.toString()
        Log.d("###mainactivity", "email is :$email")
        Log.d("###mainactivity", "email is :$password")

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

     fun performSignIn() {
        val email = email_login.text.toString()
        val password = password_log_in.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Log.d("###LOGIN", "please fill both email and password.")
            Toast.makeText(this, "please enter an email and a password.", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) return@addOnCompleteListener
                Log.d("###", "Successfully logged in")
                val intent = Intent(this, MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)// clear stack
                startActivity(intent)
            }.addOnFailureListener {
                Log.d("###LOGIN", "failed to login because ${it.message}")
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT)
            }
    }
}
