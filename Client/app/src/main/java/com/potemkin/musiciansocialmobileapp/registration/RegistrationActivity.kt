package com.potemkin.musiciansocialmobileapp.registration

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.potemkin.musiciansocialmobileapp.LoginActivity
import com.potemkin.musiciansocialmobileapp.MapActivity
import com.potemkin.musiciansocialmobileapp.R
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.android.synthetic.main.activity_place_registration.*
import kotlinx.android.synthetic.main.activity_registration.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class RegistrationActivity : AppCompatActivity() {
    var itemSelected:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val items = listOf("Музыкант","Администратор базы","Администратор площадки")

        val autoComplete : AutoCompleteTextView = findViewById(R.id.autoComplete)

        val adapter = ArrayAdapter(this,R.layout.list_item,items)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->

            itemSelected = adapterView.getItemAtPosition(i).toString()
        }
        regButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var editTextEmail = regEditTextEmailAddress.text.toString()
                var editTextPassword = regEditTextPassword.text.toString()
                var editTextLogin = regEditTextLogin.text.toString()
                var editTextPersonName = regEditTextPersonName.text.toString()
                var editTextAbout = regEditTextAbout.text.toString()
                //var user = UserModel(editTextEmail,editTextPassword,editTextLogin,editTextPersonName,"")
                //justfortest
                when(itemSelected){
                    "Музыкант"->{
                        var user = UserModel(0,editTextEmail,editTextPassword,editTextAbout,editTextLogin,editTextPersonName,"M")
                        regUser(user)
                        val i = Intent(this@RegistrationActivity, MusRegistrationActivity::class.java).apply {
                            putExtra("email", editTextEmail)
                        }

                        startActivity(i)
                    }
                    "Администратор базы"->{
                        var user = UserModel(0,editTextEmail,editTextPassword,editTextAbout,editTextLogin,editTextPersonName,"R")
                        regUser(user)
                        val i = Intent(this@RegistrationActivity, PlaceRegistrationActivity::class.java).apply {
                            putExtra("email", editTextEmail)
                        }

                        startActivity(i)
                    }
                    "Администратор площадки"->{
                        var user = UserModel(0,editTextEmail,editTextPassword,editTextAbout,editTextLogin,editTextPersonName,"C")
                        regUser(user)
                        val i = Intent(this@RegistrationActivity, PlaceRegistrationActivity::class.java).apply {
                            putExtra("email", editTextEmail)
                        }

                        startActivity(i)
                    }
                }

            }

        })
    }

    private fun regUser(user: UserModel) {
        val url = URL("http://10.0.2.2:8081/users/upload-users")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n        \"login\": \""+user.login+"\",\n        \"password\": \""+user.password+"\",\n        \"name\": \""+user.name+"\",\n        \"email\": \""+user.email+"\",\n        \"about\": \""+user.about+"\",\n        \"usersType\": \""+user.type+"\"\n    \n}"
        val requestBodyBytes: ByteArray = data.toByteArray(StandardCharsets.UTF_8)
        connection.getOutputStream().write(requestBodyBytes);
        val responseCode = connection.responseCode
        if (responseCode === HttpURLConnection.HTTP_OK) {
            val `in` = BufferedReader(InputStreamReader(connection.inputStream))
            val response: String = `in`.readLine()
            return
        } else {
            System.out.println("Error: $responseCode")
        }

    }

    fun onClickLogin(view: View) {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}