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
package com.example.android.fitguuy.landingpage


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android.fitguuy.R
import com.example.android.fitguuy.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp

//Todo problem med binding av og til, så clean eller invalided caches

//Vi har implementert appen med å bruke en aktivitet og resten fragmenter
//Med å prøve å holde oss til design mønsteret MVVM
//Grunnet problemer underveis har det blitt endel uferdig implementasjoner eller
//løsninger som bryter med MVVM prinsippet. Vi hadde ide om å holde all kommunikasjon mellom
//backend og frontend via repo'et som hadde kommunisert med dao'en og viewmodellene direkte

class MainActivity : AppCompatActivity() {
    //Gjør dette for alle viewBinding
    //Da slipper vi tull med at den mister referanse hele tiden i import
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNUSED_VARIABLE")
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialize(this)
        FirebaseApp.initializeApp(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        bottomNavigationView.setupWithNavController(
            navController
        )

    }

}
