package dev.irving.basecode.data.user

import dev.irving.basecode.domain.users.User
import kotlinx.coroutines.flow.StateFlow

interface UserRemoteSource {

    val userFlow: StateFlow<User>

    fun fetchUser(userId: String)
}
