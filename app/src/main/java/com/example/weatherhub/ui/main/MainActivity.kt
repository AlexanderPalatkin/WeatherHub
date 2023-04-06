package com.example.weatherhub.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherhub.R
import com.example.weatherhub.ui.contacts.PhoneContactsFragment
import com.example.weatherhub.ui.history.HistoryWeatherListFragment
import com.example.weatherhub.ui.maps.MapsFragment
import com.example.weatherhub.ui.weatherList.WeatherListFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }

        saveNewTokenFBInLog()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, HistoryWeatherListFragment.newInstance())
                    .addToBackStack("").commit()
            }
            R.id.work_with_content_provider -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, PhoneContactsFragment.newInstance())
                    .addToBackStack("").commit()
            }
            R.id.menu_google_maps -> {
                supportFragmentManager.apply {
                    beginTransaction()
                        .add(R.id.container, MapsFragment())
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNewTokenFBInLog() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("mylogs_push", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("mylogs_push", "$token")
        })
    }
}