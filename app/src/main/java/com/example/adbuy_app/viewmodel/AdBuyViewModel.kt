package com.example.adbuy_app.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adbuy_app.model.`object`.forgetpassword.ForgetPassword
import com.example.adbuy_app.model.`object`.login.Login
import com.example.adbuy_app.model.`object`.register.Register
import com.example.adbuy_app.model.response.forgetpassword.ForgetPasswordResponse
import com.example.adbuy_app.model.response.login.UserData
import com.example.adbuy_app.model.response.register.RegisterResponce
import com.example.adbuy_app.other.Constants.SUCCESSFUL_CODE
import com.example.adbuy_app.other.Event
import com.example.adbuy_app.other.Resource
import com.example.adbuy_app.repository.AdBuyRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class AdBuyViewModel @ViewModelInject constructor(private val adBuyRepository: AdBuyRepository) :
    ViewModel() {
    val _loginData: MutableLiveData<Event<Resource<UserData>>> = MutableLiveData()
    val loginData: LiveData<Event<Resource<UserData>>> = _loginData
    var loginResponse: UserData? = null

    val _registerData: MutableLiveData<Event<Resource<RegisterResponce>>> = MutableLiveData()
    val registerData: LiveData<Event<Resource<RegisterResponce>>> = _registerData

    var registeResponse: RegisterResponce? = null


    val _forgetPassword: MutableLiveData<Event<Resource<ForgetPasswordResponse>>> =
        MutableLiveData()
    val forgetpassword: LiveData<Event<Resource<ForgetPasswordResponse>>> = _forgetPassword
    var forgetPassword: ForgetPasswordResponse? = null

    fun loginAdBuy(login: Login) = viewModelScope.launch {
        safeLoginCall(login)
    }

    fun registerAdBuy(register: Register) = viewModelScope.launch {
        safeSignupCall(register)
    }

    fun forgetpasswordAdBuy(forgetPassword: ForgetPassword) = viewModelScope.launch {
        safeForgetPasswordCall(forgetPassword)
    }

    private fun handleLoginResponse(response: Response<UserData>): Resource<UserData> {
        if (response.body()?.status == SUCCESSFUL_CODE) {
            response.body()?.let { resultResponse ->

                loginResponse = resultResponse
                return Resource.success(loginResponse ?: resultResponse)
            }
        }
        return Resource.error(response.body()?.message.toString(), null)
    }

    private fun handleRegisterResponse(response: Response<RegisterResponce>): Resource<RegisterResponce> {
        if (response.body()?.status == SUCCESSFUL_CODE) {
            response.body()?.let { resultResponse ->

                registeResponse = resultResponse
                return Resource.success(registeResponse ?: resultResponse)
            }
        }
        return Resource.error(response.body()?.message.toString(), null)
    }

    private fun handleForgetPasswordResponse(response: Response<ForgetPasswordResponse>): Resource<ForgetPasswordResponse> {
        if (response.body()?.status == SUCCESSFUL_CODE) {
            response.body()?.let { resultResponse ->
                forgetPassword = resultResponse
                return Resource.success(forgetPassword ?: resultResponse)

            }

        }
        return Resource.error(response.body()?.errors.toString(), null)
    }


    suspend fun safeLoginCall(login: Login) {
        _loginData.postValue(Event(Resource.loading(null)))
        try {
            val response = adBuyRepository.loginUser(login)
            _loginData.postValue(Event(handleLoginResponse(response)))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> _loginData.postValue(
                    Event(
                        Resource.error(
                            "Network Failure",
                            null
                        )
                    )
                )
                else -> _loginData.postValue(Event(Resource.error("Conversion Error", null)))
            }
        }
    }

    suspend fun safeSignupCall(
        register: Register
    ) {
        _registerData.postValue(Event(Resource.loading(null)))
        try {
            val response = adBuyRepository.signUp(register)
            _registerData.postValue(Event(handleRegisterResponse(response)))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> _registerData.postValue(
                    Event(
                        Resource.error(
                            t.message.toString(),
                            null
                        )
                    )
                )
                else -> _registerData.postValue(Event(Resource.error("Conversion Error", null)))
            }
        }
    }


    suspend fun safeForgetPasswordCall(forgetPassword: ForgetPassword) {
        _forgetPassword.postValue(Event(Resource.loading(null)))
        try {
            val response = adBuyRepository.ForgetPassword(forgetPassword)
            _forgetPassword.postValue(Event(handleForgetPasswordResponse(response)))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> _forgetPassword.postValue(
                    Event(
                        Resource.error(
                            t.message.toString(),
                            null
                        )
                    )
                )
                else -> _forgetPassword.postValue(Event(Resource.error("Conversion Error", null)))
            }
        }
    }


}