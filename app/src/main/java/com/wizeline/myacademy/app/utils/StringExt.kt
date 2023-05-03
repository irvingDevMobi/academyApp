package com.wizeline.myacademy.app.utils

fun String.hasCapitalLetter(): Boolean {
    for (letter in this.toCharArray()) {
        if (letter.isUpperCase()) return true
    }
    return false
}


