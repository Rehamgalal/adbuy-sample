package com.example.adbuy_app.api

import com.example.adbuy_app.model.`object`.forgetpassword.ForgetPassword
import com.example.adbuy_app.model.`object`.login.Login
import com.example.adbuy_app.model.`object`.register.Register
import com.example.adbuy_app.model.response.forgetpassword.ForgetPasswordResponse
import com.example.adbuy_app.model.response.login.UserData
import com.example.adbuy_app.model.response.register.RegisterResponce
import retrofit2.Response
import retrofit2.http.*

interface AdBuyApi {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    suspend fun login(@Body login: Login): Response<UserData>

    @Headers("Content-Type: application/json")
    @POST("user/register")
    suspend fun signup(@Body register: Register): Response<RegisterResponce>

    @Headers("Content-Type: application/json")
    @POST("password/email")
    suspend fun forgetPassword(@Body forgetPassword: ForgetPassword): Response<ForgetPasswordResponse>
}