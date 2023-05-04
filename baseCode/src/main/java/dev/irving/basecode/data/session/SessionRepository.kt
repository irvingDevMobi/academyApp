package dev.irving.basecode.data.session

import dev.irving.basecode.domain.session.Session
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val localSource: SessionLocalSource
) {
    suspend fun get(): Session {
        return localSource.get()
    }

    suspend fun save(session: Session) {
        localSource.save(session)
    }
}
