package com.thesis.android_challenge_w3.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thesis.android_challenge_w3.activity.boarding.BoardingOneActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this,
            BoardingOneActivity::class.java)
        startActivity(intent)
    }
}