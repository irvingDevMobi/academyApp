package dev.irving.basecode.data.session

import dev.irving.basecode.domain.session.Session
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
