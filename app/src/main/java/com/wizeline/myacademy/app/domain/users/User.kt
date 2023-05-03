package com.wizeline.myacademy.app.domain.users

data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val type: UserType = UserType.UNKNOWN
)
