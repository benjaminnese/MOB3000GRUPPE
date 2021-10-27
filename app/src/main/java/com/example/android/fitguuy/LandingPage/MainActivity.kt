/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fitguuy.LandingPage

// Test git

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.android.fitguuy.Autenfication.GoogleSignInActivity
import com.example.android.fitguuy.Autenfication.SigninWelcomePage
import com.example.android.fitguuy.Autenfication.SignupFragment
import com.example.android.fitguuy.Autenfication.TitleFragment
import com.example.android.fitguuy.Record.RecordFragment
import com.example.android.fitguuy.WorkoutHistory.HistoryFragmentContainer
import com.example.android.fitguuy.WorkoutHistory.HistoryFragment
import com.example.android.fitguuy.WorkoutSession.EditWorkOut
import com.example.android.fitguuy.WorkoutSession.RegisterWorkout
import com.google.firebase.auth.FirebaseUser
import fitguuy.R
import fitguuy.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val TitleFragment = TitleFragment()
    private val EditWorkout = EditWorkOut()
    private val HistoryFragment = HistoryFragment()
    private val WorkOutSite = RegisterWorkout()
    private val recordFragment = RecordFragment()
    private val SignupFragment = SignupFragment()
    private val SigninWelcomePage = SigninWelcomePage()
    private val historyActivity = HistoryFragmentContainer()
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        (supportActionBar?.hide()) //Gjemmer header title
        replaceFragment(TitleFragment)
        //val navView: BottomNavigationView = binding.bottomNavigation
        NavigationUI.setupActionBarWithNavController(this,navController)
        //navView.setupWithNavController(navController)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.homeScreen ->replaceFragment(TitleFragment)
                R.id.workoutScreen ->replaceFragment(recordFragment)
                R.id.historyFragment -> replaceFragment(historyActivity)
                //R.id.aboutApp -> replaceFragment(AboutFragment)
            }
            true
        }



    }
    private fun startNewActivity(){
        val intent = Intent(this, HistoryFragmentContainer::class.java)
        startActivity(intent)
    }

    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "You Signed In successfully", Toast.LENGTH_LONG).show()
            replaceFragment(SigninWelcomePage)
        } else {
            Toast.makeText(this, "You Didnt signed in", Toast.LENGTH_LONG).show()
        }
    }


    private fun replaceFragment(fragment: Fragment){
        if(fragment ==HistoryFragment){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.myNavHostFragment, fragment, "history_workout")
            transaction.commit()
        }else{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.myNavHostFragment, fragment)
            transaction.commit()
        }
    }

    fun decrementButton(view: View){
        var btnView: Button = view as Button
        var numberString: String = btnView.text as String
        var numberOfReps : Int = numberString.toInt()
        numberOfReps = numberString.toInt() - 1
        if(numberOfReps>=0){
            btnView.text = numberOfReps.toString()
            btnView.setBackgroundResource(fitguuy.R.drawable.roundbuttonselected)
        } else {
            numberOfReps = numberString.toInt() + 5
            btnView.text = numberOfReps.toString()
            btnView.setBackgroundResource(fitguuy.R.drawable.roundbuttonselected)
        }

    }

    fun editWorkOut(view: View){
        replaceFragment(EditWorkout)
    }
    fun resetWorkOut(view: View){

        refreshFragment(WorkOutSite)
    }
    fun register(view: View){
        replaceFragment(SignupFragment)
    }
    fun signin(view: View){
        replaceFragment(TitleFragment)
    }

    private fun refreshFragment(fragment: Fragment){
        val transaction = supportFragmentManager
        transaction.beginTransaction().detach(fragment).attach(fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
//    private fun setupBottomNavMenu(navController: NavController) {
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNav?.setupWithNavController(navController)
//    }
    //Navbaren er gjemt frem til bruker trykker login


    fun showNavBar(view: android.view.View) {
        binding.bottomNavigation.visibility = View.VISIBLE
        view.visibility = View.GONE

        //TODO Fjern registerknappen også
    }
    fun googleLogin(view: android.view.View){
        val intent = Intent(this, GoogleSignInActivity::class.java)
        startActivity(intent)
    }



}
