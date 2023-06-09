package com.wizeline.myacademy.app.data.user

import com.wizeline.myacademy.app.domain.users.User
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userRemoteSource: UserRemoteSource
){
    val userFlow: StateFlow<User> get() = userRemoteSource.userFlow

    fun fetchUser(userId: String) {
        userRemoteSource.fetchUser(userId = userId)
    }
}
