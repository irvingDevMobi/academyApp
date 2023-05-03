package com.wizeline.myacademy.app.data.session

import com.wizeline.myacademy.app.domain.session.Session
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
