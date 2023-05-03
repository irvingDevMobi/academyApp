package com.wizeline.myacademy.app.domain.session

data class Session(
    val token: String = "",
    val expirationDate: String = "",
    val refreshToken: String = "",
    val currentUserId: String = ""
)
