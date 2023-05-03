package com.wizeline.myacademy.app.data.session

import com.wizeline.myacademy.app.domain.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SessionFakeSource(
    private val dispatcher: CoroutineDispatcher
) : SessionLocalSource {

    private var currentSession = Session()

    override suspend fun get(): Session = withContext(dispatcher) {
        currentSession
    }

    override suspend fun save(session: Session) = withContext(dispatcher) {
        currentSession = session
    }
}
