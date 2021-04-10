package com.thesis.android_challenge_w3.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w3.R
import com.thesis.android_challenge_w3.databinding.ActivitySignInBinding
import com.thesis.android_challenge_w3.model.SignInViewModel
import com.thesis.android_challenge_w3.store.DataStore

class SignInActivity : AppCompatActivity() {

    companion object {
        const val USER_KEY = "USER_KEY"
    }

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)

        binding.apply {

            btnSignIn.setOnClickListener {
                viewModel.user.email = edtEmail.text.toString().trim()
                viewModel.user.password = edtPassword.text.toString().trim()
                if(DataStore.instance.isLoginSucceed(viewModel.user.email, viewModel.user.password)){
                    Toast.makeText(this@SignInActivity, "Sign in Succeed", Toast.LENGTH_SHORT).show()

                    val bundle = Bundle()
                    bundle.putParcelable(USER_KEY, DataStore.instance.currentUser)
                    val intent = Intent(this@SignInActivity, ProfileActivity::class.java)
                    intent.putExtras(bundle)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "Sign in failed", Toast.LENGTH_SHORT).show()

                }
            }

            btnSignUp.setOnClickListener {
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

        }
    }

}