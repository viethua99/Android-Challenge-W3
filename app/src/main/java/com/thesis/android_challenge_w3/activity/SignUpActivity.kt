package com.thesis.android_challenge_w3.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.thesis.android_challenge_w3.R
import android.widget.TextView

/**
 * Created by Viet Hua on 04/03/2021.
 */

class SignUpActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_up)


        val btnSignIn = findViewById<TextView>(R.id.btn_sign_in)
        btnSignIn.setOnClickListener {
            val intent = Intent(this,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }
}