package com.wizeline.myacademy.app.data.analytics

import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class GoogleAnalyticsRepository : AnalyticsRepository {
    override fun logEvent(eventName: String, params: Bundle) {
        Firebase.analytics.logEvent(eventName, params)
    }
}
