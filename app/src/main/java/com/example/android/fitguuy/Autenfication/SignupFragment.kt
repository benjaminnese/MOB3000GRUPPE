package com.example.android.fitguuy.Autenfication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import fitguuy.R


class SignupFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var name: TextView
    private lateinit var email: EditText
    private lateinit var editNam: EditText
    private lateinit var editPassword: EditText
    private lateinit var reEditPassword: EditText
    private lateinit var btnSignUp: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }
     private fun init() {

         email = requireView().findViewById(R.id.editTextTextEmailAddress) as EditText
         editPassword = requireView().findViewById(R.id.editTextTextPassword) as EditText
         reEditPassword = requireView().findViewById(R.id.editTextTextPasswordRepeat) as EditText
         btnSignUp = requireView().findViewById(R.id.btnSignUp) as Button
         btnSignUp.setOnClickListener(View.OnClickListener {
             if (!reEditPassword.getText().toString().equals(editPassword.getText().toString())) {
                 Toast.makeText(
                     activity,
                     "Password and confirm password not same. Please try again.",
                     Toast.LENGTH_SHORT
                 ).show()
                 return@OnClickListener
             }
            val emailID = email.text.toString()
            val paswd: String = editPassword.getText().toString()
            if (emailID.isEmpty()) {
                email.error = "Provide your Email first!"
                email.requestFocus()
            } else if (paswd.isEmpty()) {
                editPassword.setError("Set your password")
                editPassword.requestFocus()
            } else if (emailID.isEmpty() && paswd.isEmpty()) {
                Toast.makeText(activity, "Fields Empty!", Toast.LENGTH_SHORT).show()
            } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                mAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(
                    requireActivity()
                ) { task ->
                    if (task.isSuccessful) {

                    } else {
                        // Do your task in failure
                    }
                }
            }
        })
    }

    companion object {
}}