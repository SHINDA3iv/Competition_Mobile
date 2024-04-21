package com.example.parkingapp.data.remote

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


fun saveUser(context: Context, user: User) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = gson.toJson(user)
    editor.putString("user", json)
    editor.apply()
}

fun loadUser(context: Context): User? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json: String? = sharedPreferences.getString("user", null)
    return gson.fromJson(json, User::class.java)
}
