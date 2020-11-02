package com.example.userapp

import android.content.Context

class PreferencesHelper(context: Context) {

    private val fileName = "mis_preferencias"

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

}