package rma.studentattendance.ui

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import rma.studentattendance.databinding.ActivityMainBinding

import rma.studentattendance.R

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "12345"
    private val description = "Test Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager
        makeNotification()

    }
    fun makeNotification() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
        notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)
        builder = Notification.Builder(this, channelId).setContentTitle("StudentAttendanceTracker").setContentText("Welcome to student attendance apk").setSmallIcon(R.drawable.ic_app_icon).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_app_icon)).setContentIntent(pendingIntent)
    }
    notificationManager.notify(2, builder.build())
}
}









