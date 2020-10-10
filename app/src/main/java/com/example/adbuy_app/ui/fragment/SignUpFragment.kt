package com.example.adbuy.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.adbuy_app.R
import com.example.adbuy_app.model.`object`.register.Register
import com.example.adbuy_app.other.Constants
import com.example.adbuy_app.other.Status
import com.example.adbuy_app.other.Validation
import com.example.adbuy_app.viewmodel.AdBuyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_up.*
import timber.log.Timber


@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {


    private val viewModel: AdBuyViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        skip.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }
        login.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpToSignIn())
        }


        signup_button.setOnClickListener {
            if (Validation.validateRegistrationInput(
                    tvfirstname,
                    tvlastname,
                    email,
                    password,
                    confirm_password,
                    phone_num
                )
            ) {
                viewModel.registerAdBuy(
                    Register(
                        tvfirstname.text.toString(),
                        tvlastname.text.toString(),
                        email.text.toString(),
                        password.text.toString(),
                        confirm_password.text.toString(),
                        phone_num.text.toString()
                    )
                )
            } else {
                Validation.validateRegistrationInputError(
                    tvfirstname,
                    tvlastname,
                    email,
                    password,
                    confirm_password,
                    phone_num
                )
            }
        }

        viewModel.registerData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->

                when (result.status) {
                    Status.SUCCESS -> {
                        prSignup.hide()
                        Snackbar.make(
                            requireView(),
                            Constants.SUCCESSFUL_RESPONSE,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Status.ERROR -> {
                        result.message?.let {
                            prSignup.hide()
                            Snackbar.make(requireView(), "Unauthorised", Snackbar.LENGTH_LONG)
                                .show()

                            Timber.e(it)

                        }


                    }
                    Status.LOADING -> {
                        prSignup.show()
                    }
                }
            }
        })


    }


}