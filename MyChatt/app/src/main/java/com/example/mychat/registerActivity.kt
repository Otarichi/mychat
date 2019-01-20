package com.example.mychat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regButton.setOnClickListener { registration() }
        regLoginPageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    fun registration() {
        var name = regNameEditText.text.toString()
        var email = regEmailEditText.text.toString()
        var password = regPasswordEditText.text.toString()

        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "თქვენ წარმატებით გაიარეთ რეგისტრაცია, გთხოვთ გაიაროთ ავტორიზაცია", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "რეგისტრაცია ვერ მოხერხდა", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}
