package com.example.myapplication

import android.Manifest.permission.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.util.PermisionHelper


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermisionHelper.askPermision(this, arrayOf(
            WRITE_EXTERNAL_STORAGE,
            READ_EXTERNAL_STORAGE
        ), object : PermisionHelper.PermisionResult {
            override fun grantPermision() {
                Toast.makeText(applicationContext,"grant",Toast.LENGTH_SHORT).show()
            }

            override fun deniedpermision() {
                Toast.makeText(applicationContext,"denied",Toast.LENGTH_SHORT).show()

            }

        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermisionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
