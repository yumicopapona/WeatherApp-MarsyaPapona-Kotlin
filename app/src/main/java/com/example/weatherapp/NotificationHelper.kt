package com.example.weatherapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    private val channelId = "BringUmbrellaChannel"
    private val channelName = "Bring Umbrella"

    private val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, channelId)
        .setContentTitle("Bawa Payung!!")
        .setContentText("Hujan akan Datang!!")
        .setSmallIcon(R.drawable.umbrella)


    @RequiresApi(Build.VERSION_CODES.O)
    fun startNotification() {



        if (notificationManager.getNotificationChannel(channelId) == null) {
            val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }


        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)


        notificationBuilder.setContentIntent(pendingIntent)


        notificationManager.notify(1, notificationBuilder.build())


        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            startNotification()
        }
        handler.postDelayed(runnable, 50000)
    }
}