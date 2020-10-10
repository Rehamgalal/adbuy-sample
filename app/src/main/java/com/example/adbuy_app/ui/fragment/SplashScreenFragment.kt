package com.example.adbuy.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.adbuy_app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.fragment_splash) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @set:Inject
    var isFirstAppOpen = true
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstAppOpen) {

            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    delay(3000)
                    findNavController()
                        .navigate(
                            R.id.action_splashFragment_to_signUpFragment,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.splashFragment,
                                    true
                                ).build()
                        )
                }
            }

        } else {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    delay(3000)
                    findNavController()
                        .navigate(
                            R.id.action_Splash_to_OnBoarding,
                            null,
                            NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.splashFragment,
                                    true
                                ).build()
                        )
                }

            }
        }

    }
}