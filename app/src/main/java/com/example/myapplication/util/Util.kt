package com.example.myapplication.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.getSystemService
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.snackbar.Snackbar

object Util {

    private var dialog: AlertDialog? = null

    fun showProgressDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false) // if you want user to wait for some process to finish,
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(com.example.myapplication.R.layout.layout_loading_dialog, null)
        builder.setView(v)
        dialog = builder.create()
    }

    fun hideProgressDialog() {
        dialog!!.dismiss()
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI ||  connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE

        }
    }

    fun showSnackBar(view: View, message:String) {
        val snackbar = Snackbar
            .make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
        }


}