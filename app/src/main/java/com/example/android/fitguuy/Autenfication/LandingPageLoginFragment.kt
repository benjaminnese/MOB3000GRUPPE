package com.example.android.fitguuy.Autenfication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.fitguuy.LandingPage.MainActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import fitguuy.R
import fitguuy.databinding.FragmentLandingPageLoginBinding





class TitleFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnSignIn: Button
    private lateinit var btnForgotPassword: TextView
    private lateinit var binding: FragmentLandingPageLoginBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mAuth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_landing_page_login,container,false)
//        binding.playButton.setOnClickListener{view : View ->
//            view.findNavController().navigate(R.id.mainActivity)}
       return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {

        email = requireView().findViewById(R.id.editTextTextEmailAddress) as EditText
        editPassword = requireView().findViewById(R.id.editTextTextPassword) as EditText
        btnSignIn = requireView().findViewById(R.id.btnLogIn) as Button
        btnForgotPassword = requireView().findViewById(R.id.textViewForgotPassword) as TextView


        btnForgotPassword.setOnClickListener {
            if (email.text.toString().isEmpty()){
                email.error = "What is your email"
                email.requestFocus()
            }

            else{
                mAuth.sendPasswordResetEmail(email.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                    } else {
                    }
                }
            }
        }

        btnSignIn.setOnClickListener(View.OnClickListener {

            val emailID = email.text.toString()
            val paswd: String = editPassword.getText().toString()
            if (emailID.isEmpty()) {
                email.error = "Provide your Email first!"
                email.requestFocus()
            } else if (paswd.isEmpty()) {
                editPassword.setError("your password??")
                editPassword.requestFocus()
            } else if (emailID.isEmpty() && paswd.isEmpty()) {
                Toast.makeText(activity, "Fields Empty!", Toast.LENGTH_SHORT).show()
            } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                activity?.let { it1 ->
                    mAuth.signInWithEmailAndPassword(emailID, paswd)
                        .addOnCompleteListener(it1) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success")
                                val user = mAuth.currentUser
                                (activity as MainActivity).updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                                (activity as MainActivity).updateUI(null)
                            }
                        }
                }
        }})
    }
}

private fun TextView.setOnClickListener(sendPasswordResetEmail: Task<Void>) {

}
