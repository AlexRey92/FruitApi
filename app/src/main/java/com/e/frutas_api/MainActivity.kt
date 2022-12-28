package com.e.frutas_api

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.appcompat.widget.SearchView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity()  {
    private lateinit var recyclerView: RecyclerView
    private  var listofFruits= listOf<Fruit>()
    private lateinit var adapter:FruitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=adapter
        getRetrofit()
        CreateNotificationChannel()
        CreateNotification()
        getListofFruits()

    }

    private fun getListofFruits() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java).getAllFruits()
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    listofFruits= response?.fruits?.map { Fruits -> Fruits.MapFruit() }!!
                } else {
                  listofFruits= emptyList()
                }


            }
        }
    }

    private fun CreateNotification() {
        val intent= Intent(this,MainActivity::class.java)
        val pendingIntent=TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification=NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_self_improvement_24)
            .setContentTitle("FruityVice")
            .setContentText("Fruit Added")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManager=NotificationManagerCompat.from(this)
        notificationManager.notify(0,notification)

    }

    private fun CreateNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel= NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT).apply {
                enableVibration(true)
                enableLights(true)
            }
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)

        }
    }

    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_FRUIT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    companion object{
        const val URL_FRUIT="https://www.fruityvice.com/api/fruit/"
        const val CHANNEL_ID="Channel_ID"
        const val CHANNEL_NAME="Channel_NAME"

    }
}