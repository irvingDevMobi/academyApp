package com.wizeline.myacademy.app.data.analytics

import android.os.Bundle

interface AnalyticsRepository {
    fun logEvent(eventName: String, params: Bundle)
}
