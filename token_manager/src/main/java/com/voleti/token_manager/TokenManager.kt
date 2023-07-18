package com.voleti.token_manager

import android.content.Context

class TokenManager (private val context: Context) {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("token_manager", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String {
        // Get token from shared preferences
        return sharedPreferences.getString("token", "") ?: ""
    }

    fun deleteToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}