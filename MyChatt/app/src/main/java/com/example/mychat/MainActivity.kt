package com.example.mychat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener { login() }
        loginRegisterPageButton.setOnClickListener {
            val intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
        }
    }
    fun login() {
        var email = loginEmailEditText.text.toString()
        var password = loginPasswordEditText.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, timelineActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "ელ-ფოსტა ან პაროლი არასწორია", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
