package com.example.android.fitguuy.autenfication


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.android.fitguuy.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


/**
// * Kode som er hentet fra nettet og modifsert etter behovet vårt
//   https://github.com/jirawatee/FirebaseAuth-Android/blob/master/app/src/main
//   /java/com/example/auth/GoogleSignInActivity.java
// */
class GoogleSignInActivity : Fragment() {

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        Log.i(TAG, "GoogleSignInActivity starter")
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        val btnSignInWithGoogle = requireView().findViewById(R.id.btnSignInGoogle) as SignInButton
        btnSignInWithGoogle.setSize(SignInButton.SIZE_ICON_ONLY)
        btnSignInWithGoogle.setOnClickListener {
            signIn()
        }
        auth = FirebaseAuth.getInstance()
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }

    //TODO Fikk ikke til google sign inn igjen etter vi byttet den over til fragment fra activity
    private fun signIn() {
        Log.i(TAG, "Kjørt metoden signIn")
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build() // her listes alle metoder å logge inn med. Kan legges til flere
        )
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN)
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    companion object {
        private const val TAG = "Google Fragment"
        private const val RC_SIGN_IN = 9001
    }
}


