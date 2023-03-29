package com.example.weatherhub.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.weatherhub.R
import com.example.weatherhub.lesson9.WorkWithContentProviderFragment
import com.example.weatherhub.ui.history.HistoryWeatherListFragment
import com.example.weatherhub.ui.weatherList.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }

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
                    .add(R.id.container, WorkWithContentProviderFragment.newInstance())
                    .addToBackStack("").commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}