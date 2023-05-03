package com.wizeline.myacademy.app.data.session

import com.wizeline.myacademy.app.domain.session.Session

interface SessionLocalSource {
    suspend fun get(): Session
    suspend fun save(session: Session)
}
