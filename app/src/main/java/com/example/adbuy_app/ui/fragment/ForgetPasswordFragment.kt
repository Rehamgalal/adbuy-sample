package com.example.adbuy_app.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.adbuy_app.R
import com.example.adbuy_app.model.`object`.forgetpassword.ForgetPassword
import com.example.adbuy_app.other.Status
import com.example.adbuy_app.other.Validation
import com.example.adbuy_app.viewmodel.AdBuyViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forget_pssword.*

@AndroidEntryPoint
class ForgetPssword : Fragment(R.layout.fragment_forget_pssword) {

    private val viewModel: AdBuyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObsrve()
        signup.setOnClickListener {
            findNavController().navigate(ForgetPsswordDirections.actionForgetPsswordToSignUp())
        }

        bCompleteforgetPassword.setOnClickListener {

            val forgetpassword = ForgetPassword(etEmailForgetPassword.text.toString())
            if (Validation.validationForgetPasswordInput(etEmailForgetPassword)) {
                viewModel.forgetpasswordAdBuy(forgetpassword)
            } else {
                Validation.validationForgetPasswordInputError(etEmailForgetPassword)
            }
        }


    }

    private fun subscribeToObsrve() {
        viewModel.forgetpassword.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        prForgetPassword.hide()

                        Snackbar.make(
                            requireView().rootView,
                            result.data?.message ?: "Done",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    Status.ERROR -> {
                        prForgetPassword.hide()

                        Snackbar.make(
                            requireView().rootView,
                            result.message.toString(),
                            Snackbar.LENGTH_LONG
                        ).show()


                    }

                    Status.LOADING -> {
                        prForgetPassword.show()
                    }

                }
            }
        })
    }
}