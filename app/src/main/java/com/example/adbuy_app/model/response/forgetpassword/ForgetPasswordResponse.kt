package com.example.adbuy_app.model.response.forgetpassword

import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponse(

	@field:SerializedName("response")
	val response: Response? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("errors")
	val errors: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class Response(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("meta")
	val meta: Any? = null
)
