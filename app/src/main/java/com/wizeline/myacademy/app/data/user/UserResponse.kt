package com.wizeline.myacademy.app.data.user

import com.wizeline.myacademy.app.domain.users.User
import com.wizeline.myacademy.app.domain.users.UserType

data class UserResponse(
    val firstName: String = "",
    val lastName: String = "",
    val type: String = ""
) {
    fun toUser(userId: String): User {
        return User(
            userId,
            firstName,
            lastName,
            UserType.fromString(type)
        )
    }
}
