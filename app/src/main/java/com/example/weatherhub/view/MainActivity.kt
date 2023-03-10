package com.example.weatherhub.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherhub.R
import com.example.weatherhub.view.weatherList.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }
}