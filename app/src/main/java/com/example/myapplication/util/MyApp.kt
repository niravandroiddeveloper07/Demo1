package com.example.myapplication.util

import android.app.Application
import android.content.SharedPreferences
import com.example.myapplication.util.PreferenceHelper.PREFCUSTOM

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        pref= PreferenceHelper.customPrefs(this,PREFCUSTOM)

    }

    companion object
    {
        lateinit var pref:SharedPreferences

    }
}