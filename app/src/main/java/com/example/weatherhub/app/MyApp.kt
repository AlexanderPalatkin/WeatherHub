package com.example.weatherhub.app

import android.app.Application
import androidx.room.Room
import com.example.weatherhub.domain.room.HistoryDao
import com.example.weatherhub.domain.room.HistoryDB

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {
        private var db: HistoryDB? = null
        private var appContext: MyApp? = null
        private const val DB_NAME = "History.db"
        fun getHistoryDao(): HistoryDao {
            if (db == null) {

                if (appContext != null) {
                    db = Room.databaseBuilder(
                        appContext!!.applicationContext,
                        HistoryDB::class.java,
                        DB_NAME
                    ).allowMainThreadQueries()
                        .build()
                } else {
                    throw IllegalStateException("Application is null while creating DataBase")
                }
            }
            return db!!.historyDao()
        }
    }
}