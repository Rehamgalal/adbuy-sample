package com.example.adbuy_app.model.response.register

data class RegisterResponce(
	val response: Response? = null,
	val message: String? = null,
	val errors: Any? = null,
	val status: Int? = null
)

data class Data(
	val mobile: Int? = null,
	val lastName: String? = null,
	val id: Int? = null,
	val firstName: String? = null,
	val email: String? = null,
	val token: String? = null
)

data class Response(
	val data: Data? = null,
	val meta: Any? = null
)

