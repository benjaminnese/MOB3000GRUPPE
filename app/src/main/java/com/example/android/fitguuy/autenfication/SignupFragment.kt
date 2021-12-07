package com.example.android.fitguuy.autenfication


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.android.fitguuy.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException


class SignupFragment : androidx.fragment.app.Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var editPassword: EditText
    private lateinit var reEditPassword: EditText
    private lateinit var btnSignUp: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing_page_register, container, false)
        val btnBackToLogin = view.findViewById<Button>(R.id.btnBackToLoginSite)
        mAuth = FirebaseAuth.getInstance()

        btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_landingPageLoginFragment)
        }
        return view
    }
    //Sjekker gyldighet av inndata og om bruker eksistere fra før
    override fun onStart() {
        super.onStart()
        email = requireView().findViewById(R.id.editTextSignUpEmail) as EditText
        editPassword = requireView().findViewById(R.id.editTextTextPassword) as EditText
        reEditPassword = requireView().findViewById(R.id.editTextTextPasswordRepeat) as EditText
        btnSignUp = requireView().findViewById(R.id.btnRegisterSignUpPage) as Button
        btnSignUp.setOnClickListener(View.OnClickListener {
            if (reEditPassword.text.toString() != editPassword.text.toString()) {
                Toast.makeText(
                    activity,
                    "Password and confirm password not same. Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            val emailID = email.text.toString()
            val paswd: String = editPassword.text.toString()
            if (emailID.isEmpty()) {
                email.error = "Provide your Email first!"
                email.requestFocus()
            } else if (paswd.isEmpty()) {
                editPassword.error = "Set your password"
                editPassword.requestFocus()
            } else if (emailID.isEmpty() && paswd.isEmpty()) {
                Toast.makeText(activity, "Fields Empty!", Toast.LENGTH_SHORT).show()
            } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                Toast.makeText(activity, "Creating user", Toast.LENGTH_SHORT).show()
                mAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(
                    requireActivity()
                ) { task ->
                    Log.i("SignupFragment", task.exception.toString())
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_signupFragment_to_homePage)
                    } else if (task.exception is FirebaseAuthUserCollisionException)
                        Toast.makeText(activity, "User already exsist", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(activity, "Failed to create user", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

}