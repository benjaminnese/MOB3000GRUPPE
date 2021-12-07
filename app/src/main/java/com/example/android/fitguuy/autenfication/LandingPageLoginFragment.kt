package com.example.android.fitguuy.autenfication


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.fitguuy.R
import com.example.android.fitguuy.databinding.FragmentLandingPageLoginBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

//Hatt mye problemer med bindingen, så kan være du må clean project eller invalided caches

class LandingPageLoginFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var email: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnSignIn: MaterialButton
    private lateinit var btnForgotPassword: Button

    private var _binding: FragmentLandingPageLoginBinding? = null
    private val binding get() = _binding!!
    val username: TextView by lazy {
        view?.findViewById<TextView>(R.id.textViewWelcomeHomePage) ?: TextView(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_landing_page_login, container, false)

        _binding = FragmentLandingPageLoginBinding.inflate(
            inflater,
            container, false
        )
       // GoogleSignInActivity()

        mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser != null)
            updateUI()
        val btnSignUp = view.findViewById<Button>(R.id.btnSignUpLandingPageLogin)
        btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_landingPageLoginFragment_to_signupFragment)
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = requireView().findViewById(R.id.editTextTextEmailAddress) as EditText
        editPassword = requireView().findViewById(R.id.editTextTextPassword) as EditText
        btnSignIn = requireView().findViewById(R.id.btnLogInLandingPageLogin) as MaterialButton
        btnForgotPassword = requireView().findViewById(R.id.btnViewForgotPassword) as Button


        btnForgotPassword.setOnClickListener {
            if (email.text.toString().isEmpty()) {
                email.error = "What is your email"
                email.requestFocus()
            } else {
                mAuth.sendPasswordResetEmail(email.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Check your mail", Toast.LENGTH_SHORT)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Something when wrong with sending your mail",
                            Toast.LENGTH_SHORT
                        )
                    }
                }
            }
        }

        btnSignIn.setOnClickListener(View.OnClickListener {

            val emailID = email.text.toString().trim()
            val paswd: String = editPassword.getText().toString().trim()
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
                    mAuth.signInWithEmailAndPassword(emailID, paswd) //Får tilbake respons fra Google
                        .addOnCompleteListener(it1) { task ->
                            if (task.isSuccessful) {
                                Log.d(TAG, "signInWithEmail:success")
                                val user = mAuth.currentUser

                                if (user != null) {
                                    Log.i("LandingPageLoginFragment", user.displayName.toString())
                                    updateUI()
                                }
                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.exception)
                                Toast.makeText(
                                    activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            }
        })
    }
    //Navigere brukeren til hjemmesiden
    private fun updateUI() {
        findNavController().navigate(R.id.action_landingPageLoginFragment_to_homePage)
    }
}




