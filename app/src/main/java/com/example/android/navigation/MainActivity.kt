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

package com.example.android.navigation

// Test git

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.android.navigation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_workout.*

class MainActivity : AppCompatActivity() {
    private val TitleFragment = TitleFragment()
    private val AboutFragment = AboutFragment()
    private val CalenderWorkOutFragment = WorkOutHistory()
    private val WorkOut = WorkOut()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
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
                R.id.workoutScreen ->replaceFragment(WorkOut)
                R.id.workoutHistory -> replaceFragment(CalenderWorkOutFragment)
                //R.id.aboutApp -> replaceFragment(AboutFragment)
            }
            true
        }


    }


    fun decrementButton(view: View){
            var btnView: Button = view as Button
            var numberString: String = btnView.text as String
            var numberOfReps : Int = numberString.toInt() -1
            if(numberOfReps>=0){
                btnView.text =numberOfReps.toString()
                btnView.setBackgroundResource(R.drawable.roundbuttonselected)
            }
    }
    fun resetWorkOut(view: View){
        refreshFragment(WorkOut)
    }


    private fun replaceFragment(fragment: Fragment){
        if(fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.myNavHostFragment, fragment)
            transaction.commit()
        }
    }
    private fun refreshFragment(fragment: Fragment){
        val transaction = supportFragmentManager
        transaction.beginTransaction().detach(fragment).attach(fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setupWithNavController(navController)
    }


    // TODO (01) Create the new TitleFragment
    // Select File->New->Fragment->Fragment (Blank)

    // TODO (02) Clean up the new TitleFragment
    // In our new TitleFragment

    // TODO (03) Use DataBindingUtil.inflate to inflate and return the titleFragment in onCreateView
    // In our new TitleFragment
    // R.layout.fragment_title


}
