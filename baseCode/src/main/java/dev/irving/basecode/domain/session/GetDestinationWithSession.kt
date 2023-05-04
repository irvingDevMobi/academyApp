package dev.irving.basecode.domain.session

import dev.irving.basecode.app.Destination
import dev.irving.basecode.data.session.SessionRepository
import javax.inject.Inject

class GetDestinationWithSession @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): Destination =
        if (sessionRepository.get().token.isBlank()) Destination.LOGIN
        else Destination.DASHBOARD
}
