package com.example.testcompose

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()
fun toJson(obj: Any): String {
    return gson.toJson(obj)
}

inline fun <reified T> fromJson(json: String): T {
    return gson.fromJson<T>(json, object : TypeToken<T>() {}.type)
}