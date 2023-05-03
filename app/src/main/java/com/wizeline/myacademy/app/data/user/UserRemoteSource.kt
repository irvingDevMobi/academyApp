package com.wizeline.myacademy.app.data.user

import com.wizeline.myacademy.app.domain.users.User
import kotlinx.coroutines.flow.StateFlow

interface UserRemoteSource {

    val userFlow: StateFlow<User>

    fun fetchUser(userId: String)
}
