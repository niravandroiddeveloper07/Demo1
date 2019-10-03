package com.example.myapplication.ui

import android.Manifest.permission.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.util.PermisionHelper
import com.example.myapplication.network.ApiClient
import com.example.myapplication.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermisionHelper.askPermision(this, arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE), object : PermisionHelper.PermisionResult {

            override fun grantPermision() {
                Toast.makeText(applicationContext, "grant", Toast.LENGTH_SHORT).show()
            }

            override fun deniedpermision() {
                Toast.makeText(applicationContext, "denied", Toast.LENGTH_SHORT).show()
            }
        })

        callApi()

    }

    private fun callApi() {

        val  apiInterface = ApiClient.client!!.create(ApiInterface::class.java)
        val call=apiInterface.doGetUserList("1")

        call.enqueue(object : Callback<com.example.myapplication.model.Response> {
            override fun onResponse(call: Call<com.example.myapplication.model.Response>, response: Response<com.example.myapplication.model.Response>) {
           }

            override fun onFailure(call: Call<com.example.myapplication.model.Response>, t: Throwable) {
            }
        })

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        PermisionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
