package com.example.android.fitguuy.autenfication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.fitguuy.R
import com.google.firebase.auth.FirebaseAuth
//Enkel forside så vi hadde noe
//Fikk ikke tid til noe mer her
class HomePage : Fragment() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        mAuth = FirebaseAuth.getInstance()
        var user = mAuth.currentUser?.email
        user = user?.split("@")?.get(0)
        if (user != null) {

            view?.findViewById<TextView>(R.id.textViewWelcomeUserNameHomePage)!!.text = user
        }

        val btnSignout = view.findViewById<Button>(R.id.btnSignOutHomePage)
        btnSignout.setOnClickListener {
            mAuth.signOut()
            findNavController().navigate(R.id.action_homePage_to_navigation_fragment_landing_page_welcome_screen)
        }

        return view
    }

}