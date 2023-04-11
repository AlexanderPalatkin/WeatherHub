package com.example.weatherhub.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.weatherhub.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFBService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        if (message.data.isNotEmpty()) {
            val title = message.data[KEY_TITLE]
            val sendMessage = message.data[KEY_MESSAGE]
            if (!title.isNullOrEmpty() && !sendMessage.isNullOrEmpty()) {
                push(title, sendMessage)
            }
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    companion object {
        private const val NOTIFICATION_ID_HIGH = 1
        private const val CHANNEL_ID_HIGH = "channel_id_high"
        private const val KEY_TITLE = "myTitle"
        private const val KEY_MESSAGE = "myMessage"
    }

    private fun push(title: String, message: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notificationBuilderTest = NotificationCompat.Builder(this, CHANNEL_ID_HIGH).apply {
            setSmallIcon(R.drawable.ic_earth)
            setContentTitle(title)
            setContentText(message)
            priority = NotificationManager.IMPORTANCE_HIGH
        }

        val channelName = "Name $CHANNEL_ID_HIGH"
        val channelDescription = "Description $CHANNEL_ID_HIGH"
        val channelPriority = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID_HIGH, channelName, channelPriority).apply {
            description = channelDescription
        }
        notificationManager.createNotificationChannel(channel)

        notificationManager.notify(NOTIFICATION_ID_HIGH, notificationBuilderTest.build())
    }
}