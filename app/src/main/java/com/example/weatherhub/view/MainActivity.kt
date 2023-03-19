package com.example.weatherhub.view

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.weatherhub.R
import com.example.weatherhub.lesson6.MainService
import com.example.weatherhub.lesson6.MyBroadcastReceiver
import com.example.weatherhub.lesson6.ThreadsFragment
import com.example.weatherhub.view.weatherList.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }

//        startService(Intent(this, MainService::class.java).apply {
//            putExtra("key1", "Privet Service")
//        })
//
//        val receiver = MyBroadcastReceiver()
//        registerReceiver(receiver, IntentFilter("myaction"))
////        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("myaction"))

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.menu_threads -> {
//                supportFragmentManager.beginTransaction().replace(R.id.container, ThreadsFragment.newInstance()).commit()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
}