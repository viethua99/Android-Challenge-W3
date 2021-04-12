package com.thesis.android_challenge_w3.activity.welcome

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.thesis.android_challenge_w3.R
import com.google.android.material.button.MaterialButton
import android.widget.TextView
import com.thesis.android_challenge_w3.activity.signin.SignInActivity
import com.thesis.android_challenge_w3.activity.signup.SignUpActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var btnStart : MaterialButton
    private lateinit var btnSignIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_welcome)
        findViewsById()
        setupViews()
    }

    private fun findViewsById(){
        btnStart = findViewById(R.id.btn_start)
        btnSignIn = findViewById(R.id.btn_sign_in)
    }

    private fun setupViews(){
        btnStart.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}