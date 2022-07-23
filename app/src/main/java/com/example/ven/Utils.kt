package com.example.ven

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Utils {

    fun showError( context: Context, message: String) {
        AlertDialog.Builder(context).apply {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("Accept", null)
        }.show()
    }


}