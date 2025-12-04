package com.example.testcompose

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.net.ssl.HttpsURLConnection

object Api {
    inline fun <reified T> request(arg: String, method: String, json: String = ""): T {
        val url = URL("https://meloves.sytes.net/test/api/v1/$arg")
        val con = url.openConnection() as HttpsURLConnection
        con.apply {
            requestMethod = method
            connectTimeout = 3000
            if (json.isNotEmpty()) {
                doOutput = true
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
                OutputStreamWriter(con.outputStream, StandardCharsets.UTF_8).use { it.write(json) }
            }
        }
        if (con.responseCode !in 200..299) {
            throw Exception("Error: ${con.responseMessage}")
        }
        val resJson = BufferedReader(
            InputStreamReader(
                con.inputStream,
                StandardCharsets.UTF_8
            )
        ).use { it.readText() }
        return fromJson<T>(resJson)
    }

    inline fun <reified T> get(arg: String): T {
        return request(arg, "GET")
    }

    inline fun <reified T> post(arg: String, json: String = ""): T {
        return request(arg, "POST", json)
    }

    inline fun <reified T> put(arg: String, json: String = ""): T {
        return request(arg, "PUT", json)
    }

    inline fun <reified T> delete(arg: String): T {
        return request(arg, "DELETE")
    }
}