package dev.irving.basecode.domain.users

import dev.irving.basecode.data.session.SessionRepository
import dev.irving.basecode.data.user.UserRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository
) {
    val userFlow: StateFlow<User> get() = userRepository.userFlow

    suspend operator fun invoke() {
        val currentUserId = sessionRepository.get().currentUserId
        userRepository.fetchUser(currentUserId)
    }
}
