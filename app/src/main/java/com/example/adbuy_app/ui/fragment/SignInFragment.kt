package com.example.adbuy.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.adbuy_app.R
import com.example.adbuy_app.model.`object`.login.Login
import com.example.adbuy_app.other.Constants.SUCCESSFUL_RESPONSE
import com.example.adbuy_app.other.Status

import com.example.adbuy_app.other.Validation
import com.example.adbuy_app.viewmodel.AdBuyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_sign_in.*
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private val viewModel: AdBuyViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        password.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
//            source.toString().filterNot { it.isWhitespace() }
//        })
        signup.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInToSignUp())
        }
        forgot_password.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInToForgetPassword())
        }
        loginp_button.setOnClickListener {
            if (Validation.validateLoginInput(email, edPassword)) {
                viewModel.loginAdBuy(Login(email.text.toString(),edPassword.text.toString()))
            }else{
                Validation.validateLoginInputError(email, edPassword)
            }

        }
        viewModel.loginData.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        prForgetPassword.hide()
                        Snackbar.make(requireView(), SUCCESSFUL_RESPONSE, Snackbar.LENGTH_LONG).show()
                    }
                    Status.ERROR -> {
                        prForgetPassword.hide()
                        result.message?.let {
                            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()

                            Timber.e(it)

                        }


                    }
                    Status.LOADING -> {
                        prForgetPassword.show()
                    }
                }
            }
        })

    }






}