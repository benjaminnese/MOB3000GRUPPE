package com.example.android.fitguuy.autenfication


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.fitguuy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SigninWelcomePage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_landing_page_welcome_screen, container, false)
        val btnLogin = view.findViewById<Button>(R.id.btnLoginRegister)
        val btnWithoutUser = view.findViewById<Button>(R.id.btnNoUserSignIn)

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_fragment_landing_page_welcome_screen_to_landingPageLoginFragment)
        }
        btnWithoutUser.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_fragment_landing_page_welcome_screen_to_homePage)
        }


        return view
    }
}