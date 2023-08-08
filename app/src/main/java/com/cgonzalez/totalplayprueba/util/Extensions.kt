package com.cgonzalez.totalplayprueba.util

import android.util.Base64


fun String.encodeBase64(): String {
    val bytes = this.toByteArray()
    return Base64.encodeToString(bytes, Base64.DEFAULT)
}

fun String.decodeBase64(): String {
    val decodedBytes = Base64.decode(this, Base64.DEFAULT)
    return String(decodedBytes)
}

