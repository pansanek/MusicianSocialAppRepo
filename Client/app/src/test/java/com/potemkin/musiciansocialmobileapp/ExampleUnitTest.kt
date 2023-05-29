package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.junit.Test

import org.junit.Assert.*
import com.potemkin.musiciansocialmobileapp.models.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    internal var user:UserModel = UserModel(0,"","","","","","")

    @Test
    fun userModelCreatedCorrectly() {
        var user: UserModel =
            UserModel(1, "pansanek@email.com", "1234", "Im alex", "pansanek", "Alex", "M")
        assertEquals(1, user.id)
        assertEquals("pansanek@email.com", user.email)
        assertEquals("1234", user.password)
    }

    @Test
    fun bandModelCreatedCorrectly() {
        var band = BandModel("Metallica", "Heavy metal")
        assertEquals("Metallica", band.name)
        assertEquals("Heavy metal", band.genre)
    }

    @Test
    fun testConnection() {
        val url = URL("http://localhost:8081/users/auth-user")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        connection.getOutputStream()
        val responseCode = connection.responseCode
        assertEquals(400, responseCode)
    }

    @Test
    fun authUserTest() {
        val url = URL("http://localhost:8081/users/auth-user")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        var Email = "pansanek@gmail.com"
        var Password = "12341234"
        val data =
            "{\"usersId\": 1,\n    \"login\": \"\",\n    \"password\": \"" + Password + "\",\n    \"name\": \"\",\n    \"email\": \"" + Email + "\",\n    \"about\": \"\",\n    \"usersType\": \"\"}"
        val requestBodyBytes: ByteArray = data.toByteArray(StandardCharsets.UTF_8)
        connection.getOutputStream().write(requestBodyBytes);
        val `in` = BufferedReader(InputStreamReader(connection.inputStream))
        val response: String = `in`.readLine()
        assertEquals("OK", response)
    }

    @Test
    fun authUserBadPasswordTest() {
        val url = URL("http://localhost:8081/users/auth-user")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        var Email = "pansanek@gmail.com"
        var Password = "1234"
        val data =
            "{\"usersId\": 1,\n    \"login\": \"\",\n    \"password\": \"" + Password + "\",\n    \"name\": \"\",\n    \"email\": \"" + Email + "\",\n    \"about\": \"\",\n    \"usersType\": \"\"}"
        val requestBodyBytes: ByteArray = data.toByteArray(StandardCharsets.UTF_8)
        connection.getOutputStream().write(requestBodyBytes);
        val `in` = BufferedReader(InputStreamReader(connection.inputStream))
        val response: String = `in`.readLine()
        assertEquals("Bad password", response)
    }

    @Test
    fun authUserBadEmailTest() {
        val url = URL("http://localhost:8081/users/auth-user")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        var Email = "pansank@gmail.com"
        var Password = "12341234"
        val data =
            "{\"usersId\": 1,\n    \"login\": \"\",\n    \"password\": \"" + Password + "\",\n    \"name\": \"\",\n    \"email\": \"" + Email + "\",\n    \"about\": \"\",\n    \"usersType\": \"\"}"
        val requestBodyBytes: ByteArray = data.toByteArray(StandardCharsets.UTF_8)
        connection.getOutputStream().write(requestBodyBytes);
        val `in` = BufferedReader(InputStreamReader(connection.inputStream))
        val response: String = `in`.readLine()
        assertEquals("No such user", response)
    }


}