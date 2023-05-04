package dev.irving.basecode.data.user

import dev.irving.basecode.domain.users.User
import dev.irving.basecode.domain.users.UserType


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
