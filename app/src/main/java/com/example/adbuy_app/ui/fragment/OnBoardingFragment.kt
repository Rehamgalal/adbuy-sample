package com.example.adbuy.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.adbuy_app.R
import com.example.adbuy_app.adapter.OnBoardingAdapter
import com.example.adbuy_app.other.Constants.KEY_FIRST_TOGGLE
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.fragment_onborading.*
import javax.inject.Inject

@AndroidEntryPoint
class OnBoarding : Fragment(R.layout.fragment_onborading) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tablayout.setupWithViewPager(view_pager)
        view_pager.adapter = OnBoardingAdapter()

        button.setOnClickListener {
            swipePages(view_pager.currentItem)
        }
        skip.setOnClickListener {
            findNavController().navigate(OnBoardingDirections.actionOnboardingToSignUp())
            sharedPreferences.edit().putBoolean(KEY_FIRST_TOGGLE,false).apply()

        }
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2) {
                    button.text = "Signup"
                } else {
                    button.text = "Continue"
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }

    fun swipePages(position: Int) {
        if (position == 2) {
            findNavController().navigate(R.id.action_onboarding_to_signUp)
             sharedPreferences.edit().putBoolean(KEY_FIRST_TOGGLE,false).apply()
        } else {
            view_pager.currentItem = view_pager.currentItem + 1
        }
    }
}