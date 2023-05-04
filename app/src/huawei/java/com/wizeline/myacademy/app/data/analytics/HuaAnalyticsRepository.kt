package com.wizeline.myacademy.app.data.analytics

import android.content.Context
import android.os.Bundle
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import timber.log.Timber

class HuaAnalyticsRepository(
    context: Context
) : AnalyticsRepository {

    private var instance: HiAnalyticsInstance

    init {
        instance = HiAnalytics.getInstance(context)
    }

    override fun logEvent(eventName: String, params: Bundle) {
        Timber.d("HUAWEI LOG: $eventName -> $params")
        instance.onEvent(eventName, params)
    }
}
