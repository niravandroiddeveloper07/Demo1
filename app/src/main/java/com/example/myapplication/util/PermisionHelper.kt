package com.example.myapplication.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


object PermisionHelper {

    lateinit var permisionResult: PermisionResult

    fun askPermision(
        context: Context,
        appPermissions: Array<String>,
        permisionResult: PermisionResult
    ) {
        this.permisionResult = permisionResult

        ActivityCompat.requestPermissions(
            context as Activity,
            appPermissions,
            PERMISSIONS_REQUEST_CODE
        )
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            var deniedCount = 0;
            // Gather permission grant results
            for (i in grantResults) {
                // Add only permissions which are denied
                if (i == PackageManager.PERMISSION_DENIED) {
                    deniedCount++;
                }
            }

            // Check if all permissions are granted
            if (deniedCount == 0) {
                // Proceed ahead with the app
                this.permisionResult.grantPermision()
            } else {
                this.permisionResult.deniedpermision()
            }
        }

    }

    interface PermisionResult {
        fun grantPermision()
        fun deniedpermision()
    }

}