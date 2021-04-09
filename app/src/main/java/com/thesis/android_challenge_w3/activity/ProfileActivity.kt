package com.thesis.android_challenge_w3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

import com.thesis.android_challenge_w3.R
import com.thesis.android_challenge_w3.model.User

class ProfileActivity : AppCompatActivity() {
    private lateinit var tvUserName: TextView
    private lateinit var tvFullName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var edtData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_profile)
        findViewsById()
        getUserFromBundle()
        editInfo()
    }

    private fun getUserFromBundle() {
        val bundle = intent.extras
        bundle?.let {
            val user = bundle.getParcelable<User>(SignInActivity.USER_KEY)
            initInfo(user)
        }
    }

    private fun findViewsById() {
        tvUserName = findViewById(R.id.tv_user_name)
        tvFullName = findViewById(R.id.tv_full_name)
        tvEmail = findViewById(R.id.tv_email)
        tvPhoneNumber = findViewById(R.id.tv_phone_number)
    }

    private fun initInfo(user: User?) {
        tvUserName.text = user?.fullName
        tvFullName.text = user?.fullName
        tvEmail.text = user?.email
        tvPhoneNumber.text = user?.phoneNumber
    }

    private fun setupAlertDialog(title: String, textHint: String, textView: TextView) {
        val view: View = LayoutInflater
            .from(this)
            .inflate(R.layout.dialog_edit_info, null, false)
        edtData = view.findViewById(R.id.edt_base)
        edtData.setText(textView.text)
        edtData.hint = textHint
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(view)
            .setTitle(title)
            .setPositiveButton("OK") { dialog, _ ->
                textView.text = edtData.text
                showToastMessage(edtData.text.toString())
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()

    }

    private fun editInfo() {
        tvFullName.setOnClickListener {
            setupAlertDialog("Edit Full Name", "Enter your full name", tvFullName)
        }

        tvEmail.setOnClickListener {
            setupAlertDialog("Edit E-mail ", "Enter your e-mail", tvEmail)
        }

        tvPhoneNumber.setOnClickListener {
            setupAlertDialog("Edit Phone Number ", "Enter your phone number", tvPhoneNumber)
        }
    }

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}