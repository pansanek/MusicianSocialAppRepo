package com.potemkin.musiciansocialmobileapp.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.LoginActivity
import com.potemkin.musiciansocialmobileapp.R
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.android.synthetic.main.activity_place_registration.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class PlaceRegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_registration)
        var userEmail = intent.getStringExtra("email")
        button.setOnClickListener {  if (userEmail != null) {
            getUserData(userEmail)
        }}
    }

    private fun getUserData(email: String) {
        val url = "http://10.0.2.2:8081/users/user-by-email?email="+email
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj = JSONObject(data)
            val login = jsonObj.getString("login")
            val name = jsonObj.getString("name")
            val email = jsonObj.getString("email")
            val password = jsonObj.getString("password")
            val id = jsonObj.getInt("usersId")
            val about = jsonObj.getString("about")
            val type = jsonObj.getString("usersType")
            var user = UserModel(id,email,password,about,login,name,type)
            if(type == "R"){
            val RepAdmQueue = Volley.newRequestQueue(this)
            val RepAdmUrl = "http://10.0.2.2:8081/rep_adm/all-rep_adms"
            val RepAdmReq = StringRequest(Request.Method.GET, RepAdmUrl, { response ->
                val RepAdmData = response.toString()
                val RepAdmJsonArray = JSONArray(RepAdmData)
                val RepAdmObj = RepAdmJsonArray.getJSONObject(RepAdmJsonArray.length() - 1)
                val RepAdmJsonId = RepAdmObj.getInt("repAdmId")
                var RepAdmId = RepAdmJsonId
                regRepAdmin(RepAdmId + 1, user)

                val RepBaseQueue = Volley.newRequestQueue(this)
                val RepBaseUrl = "http://10.0.2.2:8081/rep_base/all-rep_bases"
                val RepBaseReq = StringRequest(Request.Method.GET, RepBaseUrl, { response ->
                    val RepBaseData = response.toString()
                    val RepBaseJsonArray = JSONArray(RepBaseData)
                    val RepBaseObj = RepBaseJsonArray.getJSONObject(RepBaseJsonArray.length() - 1)
                    val RepBaseJsonId = RepBaseObj.getInt("repBaseId")
                    var RepBaseId = RepBaseJsonId
                    regRepBase(RepAdmId + 1,RepBaseId+1, editTextName.text.toString(),editTextAddress.text.toString(),editTextAbout.text.toString(),user)


                },{
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                )
                RepBaseQueue.add(RepBaseReq)

            },{
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
            )
                RepAdmQueue.add(RepAdmReq)
            }
            else{
                val ConAdmQueue = Volley.newRequestQueue(this)
                val ConAdmUrl = "http://10.0.2.2:8081/con_adm/all-con_adm"
                val ConAdmReq = StringRequest(Request.Method.GET, ConAdmUrl, { response ->
                    val ConAdmData = response.toString()
                    val ConAdmJsonArray = JSONArray(ConAdmData)
                    val ConAdmObj = ConAdmJsonArray.getJSONObject(ConAdmJsonArray.length() - 1)
                    val ConAdmJsonId = ConAdmObj.getInt("conAdmId")
                    var ConAdmId = ConAdmJsonId
                    regConAdmin(ConAdmId + 1, user)

                    val ConVenQueue = Volley.newRequestQueue(this)
                    val ConVenUrl = "http://10.0.2.2:8081/con_venue/all-con_venue"
                    val ConVenReq = StringRequest(Request.Method.GET, ConVenUrl, { response ->
                        val ConVenData = response.toString()
                        val ConVenJsonArray = JSONArray(ConVenData)
                        val ConVenObj = ConVenJsonArray.getJSONObject(ConVenJsonArray.length() - 1)
                        val ConVenJsonId = ConVenObj.getInt("conVenId")
                        var ConVenId = ConVenJsonId
                        regConVen(ConAdmId + 1,ConVenId+1, editTextName.text.toString(),editTextAddress.text.toString(),editTextAbout.text.toString(),user)


                    },{
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    ConVenQueue.add(ConVenReq)
                },{
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                )
                ConAdmQueue.add(ConAdmReq)
            }
        },{
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)

    }

    private fun regRepAdmin(admId:Int,user: UserModel) {
        val url = URL("http://10.0.2.2:8081/rep_adm/create-rep_adm")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"repAdmId\": "+admId+",\n    \"users\": {\n        \"usersId\": "+user.id+",\n    	\"login\": \""+user.login+"\",\n    	\"password\": \""+user.password+"\",\n    	\"name\": \""+user.name+"\",\n    	\"email\": \""+user.email+"\",\n    	\"about\": \""+user.about+"\",\n    	\"usersType\": \"R\"\n    }\n}"
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

    private fun regConAdmin(admId:Int,user: UserModel) {
        val url = URL("http://10.0.2.2:8081/con_adm/create-con_adm")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"conAdmId\": "+admId+",\n    \"users\": {\n        \"usersId\": "+user.id+",\n    	\"login\": \""+user.login+"\",\n    	\"password\": \""+user.password+"\",\n    	\"name\": \""+user.name+"\",\n    	\"email\": \""+user.email+"\",\n    	\"about\": \""+user.about+"\",\n    	\"usersType\": \"C\"\n    }\n}"
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

    private fun regRepBase(RepAdmId:Int,id:Int,name:String,address:String,about:String, user: UserModel) {
        val url = URL("http://10.0.2.2:8081/rep_base/create-rep_base")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"repBaseId\": "+id+",\n    \"repBaseName\": \""+name+"\",\n    \"repBaseAddress\": \""+address+"\",\n    \"repBaseAbout\": \""+about+"\",\n    \"repAdm\": {\n        \"repAdmId\": "+RepAdmId+",\n        \"user\": {\n            \"usersId\": "+user.id+",\n            \"login\": \""+user.login+"\",\n            \"password\": \""+user.password+"\",\n            \"name\": \""+user.name+"\",\n            \"email\": \""+user.email+"\",\n            \"about\": \""+user.email+"\",\n            \"usersType\": \"R\"\n        }\n    }\n}"
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

    private fun regConVen(ConAdmId:Int,id:Int,name:String,address:String,about:String, user: UserModel) {
        val url = URL("http://10.0.2.2:8081/con_venue/create-con_venue")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"conVenId\": "+id+",\n    \"conVenName\": \""+name+"\",\n    \"conVenAddress\": \""+address+"\",\n    \"conVenAbout\": \""+about+"\",\n    \"conAdm\": {\n        \"conAdmId\": "+ConAdmId+",\n        \"users\": {\n            \"usersId\": "+user.id+",\n            \"login\": \""+user.login+"\",\n            \"password\": \""+user.password+"\",\n            \"name\": \""+user.name+"\",\n            \"email\": \""+user.email+"\",\n            \"about\": \""+user.email+"\",\n            \"usersType\": \"C\"\n        }\n    }\n}"
        System.out.println(data)
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

}