package com.example.myapplication.ui

import android.Manifest.permission.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.util.PermisionHelper
import com.example.myapplication.network.ApiClient
import com.example.myapplication.network.ApiInterface
import com.example.myapplication.util.MyApp
import com.example.myapplication.util.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onStart() {
        super.onStart()
    }

    override fun onClick(p0: View?) {
        when(p0!!.id)
        {
            R.id.txtImagePick->
            {
                startActivity(Intent(this@MainActivity, ImagePickerActivity::class.java))

            }
            R.id.txtCenter->
            {

                PermisionHelper.askPermision(
                    this,
                    arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
                    object : PermisionHelper.PermisionResult {

                        override fun grantPermision() {
                            if (Util.isNetworkConnected(this@MainActivity)) {
                                callApi()
                            } else {
                                Util.showSnackBar(root, getString(com.example.myapplication.R.string.check))
                            }
                            Toast.makeText(applicationContext, "grant", Toast.LENGTH_SHORT).show()
                        }

                        override fun deniedpermision() {
                            Toast.makeText(applicationContext, "denied", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtImagePick.setOnClickListener(this)
        txtCenter.setOnClickListener(this)

    }

    private fun callApi() {
        Util.showProgressDialog(this)
        val apiInterface = ApiClient.client!!.create(ApiInterface::class.java)
        val call = apiInterface.doGetUserList("1")

        call.enqueue(object : Callback<com.example.myapplication.model.Response> {
            override fun onResponse(
                call: Call<com.example.myapplication.model.Response>,
                response: Response<com.example.myapplication.model.Response>
            ) {
                Util.hideProgressDialog()
                val userlist = response.body()!!.data
                CoroutineScope(Dispatchers.IO).launch {
                    for (user in userlist) {
                        MyApp.userDao.insert(user = user)
                    }
                    startActivity(Intent(this@MainActivity, UserListActivity::class.java))
                }
            }

            override fun onFailure(
                call: Call<com.example.myapplication.model.Response>,
                t: Throwable
            ) {
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

    override fun onStop() {
        super.onStop()
    }
    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }

}
