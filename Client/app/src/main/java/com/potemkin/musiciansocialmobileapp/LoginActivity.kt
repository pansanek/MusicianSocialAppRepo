package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.potemkin.musiciansocialmobileapp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val sharePreference = getSharedPreferences("MYSHAR",Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("EMAIL","")
        val getPass = sharePreference.getString("PASS","")

        if(getEmail!=""&&getPass!=""){
            val i = Intent(this@LoginActivity, MapActivity::class.java)
            startActivity(i)
        }

        loginButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var editTextEmail = editTextEmail.text.toString()
                var editTextPassword = editTextPassword.text.toString()

                val url = URL("http://10.0.2.2:8081/users/auth-user")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                connection.setRequestProperty("Content-Type", "application/json")
                connection.doOutput = true

                val data =
                    "{\"usersId\": 1,\n    \"login\": \"\",\n    \"password\": \"" + editTextPassword + "\",\n    \"name\": \"\",\n    \"email\": \""+editTextEmail+"\",\n    \"about\": \"\",\n    \"usersType\": \"\"}"

                val requestBodyBytes: ByteArray = data.toByteArray(StandardCharsets.UTF_8)
                connection.getOutputStream().write(requestBodyBytes);
                val responseCode = connection.responseCode
                if (responseCode === HttpURLConnection.HTTP_OK) {
                    val `in` = BufferedReader(InputStreamReader(connection.inputStream))
                    val response: String = `in`.readLine()

                    if (response == "OK") {
                        val editor = sharePreference.edit()
                        editor.putString("EMAIL", editTextEmail)
                        editor.putString("PASS", editTextPassword)
                        editor.apply()
                        editor.commit()
                        val i = Intent(this@LoginActivity, MapActivity::class.java)
                        startActivity(i)

                    } else Toast.makeText(
                        this@LoginActivity,
                        "Ошибка " + response,
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    System.out.println("Error: $responseCode")
                }
            }
        })
    }



    fun onClickReg(view: View) {
        val i = Intent(this, RegistrationActivity::class.java)
        startActivity(i)
    }


}