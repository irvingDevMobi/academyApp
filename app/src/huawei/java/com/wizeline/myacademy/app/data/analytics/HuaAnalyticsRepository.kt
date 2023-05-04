package com.wizeline.myacademy.app.data.analytics

import android.os.Bundle
import timber.log.Timber

class HuaAnalyticsRepository : AnalyticsRepository {
    override fun logEvent(eventName: String, params: Bundle) {
        Timber.d("HUAWEI LOG: $eventName -> $params")
    }
}
