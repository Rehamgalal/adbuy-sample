package com.example.adbuy_app.repository

import com.example.adbuy_app.api.AdBuyApi
import com.example.adbuy_app.model.`object`.forgetpassword.ForgetPassword
import com.example.adbuy_app.model.`object`.login.Login
import com.example.adbuy_app.model.`object`.register.Register
import com.example.adbuy_app.model.response.forgetpassword.ForgetPasswordResponse
import javax.inject.Inject

class AdBuyRepository @Inject constructor(private val api: AdBuyApi) {
    suspend fun loginUser(login: Login) =
        api.login(login)

    suspend fun signUp(register: Register) =
        api.signup(register)

    suspend fun ForgetPassword(forgetPassword: ForgetPassword) =
        api.forgetPassword(forgetPassword)
}