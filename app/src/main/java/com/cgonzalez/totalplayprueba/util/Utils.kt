package com.cgonzalez.totalplayprueba.util

import android.content.Context
import android.content.DialogInterface

class Utils {
    companion object {
        fun createAlertDialog(
            context: Context,
            icon: Int,
            title: String,
            message: String
        ) {
            val alertDialogBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle(title)
                .setIcon(icon)
                .setMessage(message)
                .setPositiveButton("OK") { dialog: DialogInterface, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

}