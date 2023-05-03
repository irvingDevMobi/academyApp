package com.wizeline.myacademy.app.domain.session

import com.wizeline.myacademy.app.data.session.SessionRepository
import com.wizeline.myacademy.app.ui.app.Destination
import javax.inject.Inject

class GetDestinationWithSession @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): Destination =
        if (sessionRepository.get().token.isBlank()) Destination.LOGIN
        else Destination.DASHBOARD
}
