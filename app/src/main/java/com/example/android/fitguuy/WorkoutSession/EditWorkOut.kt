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

package com.example.android.fitguuy.WorkoutSession

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.android.fitguuy.EditWorkoutAdapter
import com.google.android.material.tabs.TabLayout
import fitguuy.R
import fitguuy.databinding.FragmentEditWorkoutBinding


// test
class EditWorkOut : Fragment() {

    private lateinit var binding: FragmentEditWorkoutBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_edit_workout, container, false
        )
        tabLayout = binding.tablayout
        viewPager = binding.viewpager

        tabLayout.setupWithViewPager(viewPager, true)

        val editWorkoutAdatper = EditWorkoutAdapter(
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        editWorkoutAdatper.addFragment(WorkoutTab1(), "Workout1")
        editWorkoutAdatper.addFragment(WorkoutTab2(), "Workout2")
        viewPager.adapter = editWorkoutAdatper

        return binding.root
    }
}

