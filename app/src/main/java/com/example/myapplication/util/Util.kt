package com.example.myapplication.util

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater


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

}