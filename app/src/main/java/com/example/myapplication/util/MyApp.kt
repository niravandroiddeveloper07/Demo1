package com.example.myapplication.util

import android.app.Application
import android.content.SharedPreferences
import com.example.android.roomwordssample.UserDao
import com.example.myapplication.database.UserDatabase
import com.example.myapplication.util.PreferenceHelper.PREFCUSTOM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        userDatabase= UserDatabase.getDatabase(this, CoroutineScope(Dispatchers.IO))
        userDao= userDatabase.wordDao()

        pref= PreferenceHelper.customPrefs(this,PREFCUSTOM)

    }

    companion object
    {
        lateinit var userDao: UserDao
        lateinit var userDatabase: UserDatabase
        lateinit var pref:SharedPreferences

    }
}