package com.tva.tempad

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import java.util.regex.Pattern

class UpiNotificationService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val text = sbn?.notification?.extras?.getCharSequence("android.text")?.toString() ?: return
        
        if (text.contains("debited", ignoreCase = true) || text.contains("sent", ignoreCase = true)) {
            val pattern = Pattern.compile("(?:Rs\\.?|INR|₹)\\s*([0-9,]+(?:\\.[0-9]{2})?)")
            val matcher = pattern.matcher(text)
            if (matcher.find()) {
                val amount = matcher.group(1)
            }
        }
    }
}
