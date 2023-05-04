package dev.irving.basecode.data.session

import dev.irving.basecode.domain.session.Session

interface SessionLocalSource {
    suspend fun get(): Session
    suspend fun save(session: Session)
}
