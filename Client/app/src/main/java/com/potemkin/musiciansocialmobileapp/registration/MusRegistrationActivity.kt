package com.potemkin.musiciansocialmobileapp.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.LoginActivity
import com.potemkin.musiciansocialmobileapp.MapActivity
import com.potemkin.musiciansocialmobileapp.ProfilePageActivity
import com.potemkin.musiciansocialmobileapp.R
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.android.synthetic.main.activity_mus_registration.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.HashMap


class MusRegistrationActivity : AppCompatActivity() {
    var genreSelected:String = ""
    var instrumentSelected:String = ""
    var regIns:Int = 0
    var regGen:Int = 0
    internal var genres:ArrayList<String> = arrayListOf()
    internal var instruments:ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mus_registration)
        var userEmail = intent.getStringExtra("email")

        val urlGen = "http://10.0.2.2:8081/genres/all-genres"
        val queueGen = Volley.newRequestQueue(this)
        val reqGen = object: StringRequest(Request.Method.GET, urlGen,
            Response.Listener<String> { response ->
                val dataGen = response.toString()
                val GenJsonArray = JSONArray(dataGen)
                for (i in 0 until GenJsonArray.length()) {
                    val GenJsonObj = GenJsonArray.getJSONObject(i)
                    val genre_name = GenJsonObj.getString("genreName")

                    genres.add(genre_name)
                }

            },
            Response.ErrorListener { System.out.println("Response is: Sorry :(") })
        {
        }
        queueGen.add(reqGen)

        val urlInst = "http://10.0.2.2:8081/instruments/all-instruments"
        val queueInst = Volley.newRequestQueue(this)
        val reqInst = object: StringRequest(Request.Method.GET, urlInst,
            Response.Listener<String> { response ->
                val dataInst = response.toString()
                val InstJsonArray = JSONArray(dataInst)
                for (i in 0 until InstJsonArray.length()) {
                    val InstJsonObj = InstJsonArray.getJSONObject(i)
                    val inst_name = InstJsonObj.getString("instName")

                    instruments.add(inst_name)
                }

            },
            Response.ErrorListener { System.out.println("Response is: Sorry :(") })
        {
        }
        queueInst.add(reqInst)

        val autoCompleteGenre : AutoCompleteTextView = findViewById(R.id.autoCompleteGenre)
        val autoCompleteInstrument : AutoCompleteTextView = findViewById(R.id.autoCompleteInstrument)

        val adapterGenres = ArrayAdapter(this,R.layout.list_item,genres)
        val adapterInstruments = ArrayAdapter(this,R.layout.list_item,instruments)

        autoCompleteGenre.setAdapter(adapterGenres)
        autoCompleteInstrument.setAdapter(adapterInstruments)

        autoCompleteGenre.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            genreSelected = adapterView.getItemAtPosition(i).toString()
        }

        autoCompleteInstrument.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            instrumentSelected = adapterView.getItemAtPosition(i).toString()
        }
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
            var user = UserModel(id,email,password,about,login,name,"M")
            val MusQueue = Volley.newRequestQueue(this)
            val MusUrl = "http://10.0.2.2:8081/musician/all-musicians"
            val MusReq = StringRequest(Request.Method.GET, MusUrl, { response ->
                val MusData = response.toString()
                val MusJsonArray = JSONArray(MusData)
                val MusObj = MusJsonArray.getJSONObject(MusJsonArray.length()-1)
                val MusJsonId = MusObj.getInt("musicianId")
                var musId = MusJsonId
                regMusician(musId+1,user)

                val GenMusQueue = Volley.newRequestQueue(this)
                val GenMusUrl = "http://10.0.2.2:8081/musician_genres/all-musician_genres"
                val GenMusReq = StringRequest(Request.Method.GET, GenMusUrl, { response ->
                    val GenMusData = response.toString()
                    val GenMusJsonArray = JSONArray(GenMusData)
                    val GenMusObj = GenMusJsonArray.getJSONObject(GenMusJsonArray.length()-1)
                    val GenMusJsonId = GenMusObj.getInt("musiciangenresId")
                    var GenMusId = GenMusJsonId
                    val GenQueue = Volley.newRequestQueue(this)
                    val GenUrl = "http://10.0.2.2:8081/genres/all-genres"
                    val GenReq = StringRequest(Request.Method.GET, GenUrl, { response ->
                        val GenData = response.toString()
                        val GenJsonArray = JSONArray(GenData)
                        for (i in 0 until GenJsonArray.length()) {
                            val GenObj = GenJsonArray.getJSONObject(i)
                            val GenJsonId = GenObj.getInt("genreId")
                            val GenJsonName = GenObj.getString("genreName")
                            var GenId = GenJsonId
                            var GenName = GenJsonName
                            if (GenName == genreSelected && regGen ==0) {
                                regGen=1
                               regMusGenre(musId+1, user, GenMusId+1, GenId, GenName)
                            }
                        }
                    }, {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    GenQueue.add(GenReq)
                }, {
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                )
                GenMusQueue.add(GenMusReq)

                val InstMusQueue = Volley.newRequestQueue(this)
                val InstMusUrl = "http://10.0.2.2:8081/musician_instruments/all-musician_instruments"
                val InstMusReq = StringRequest(Request.Method.GET, InstMusUrl, { response ->
                    val InstMusData = response.toString()
                    val InstMusJsonArray = JSONArray(InstMusData)
                    val InstMusObj = InstMusJsonArray.getJSONObject(InstMusJsonArray.length()-1)
                    val InstMusJsonId = InstMusObj.getInt("musicianInstrumentsId")
                    var InstMusId = InstMusJsonId
                    val InstQueue = Volley.newRequestQueue(this)
                    val InstUrl = "http://10.0.2.2:8081/instruments/all-instruments"
                    val InstReq = StringRequest(Request.Method.GET, InstUrl, { response ->
                        val InstData = response.toString()
                        val InstJsonArray = JSONArray(InstData)
                        for (i in 0 until InstJsonArray.length()) {
                            val InstObj = InstJsonArray.getJSONObject(i)
                            val InstJsonId = InstObj.getInt("instId")
                            val InstJsonName = InstObj.getString("instName")
                            var InstId = InstJsonId
                            var InstName = InstJsonName
                            if (InstName == instrumentSelected && regIns ==0) {
                                regIns=1
                                regMusInstrument(musId+1, user, InstMusId+1, InstId, InstName)
                            }
                        }
                    }, {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    InstQueue.add(InstReq)
                }, {
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                )
                InstMusQueue.add(InstMusReq)

            }, {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
            )
            MusQueue.add(MusReq)
        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    private fun regMusician(musId:Int,user: UserModel) {
        val url = URL("http://10.0.2.2:8081/musician/create-musician")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"musicianId\": "+musId+",\n    \"users\": {\n        \"usersId\": "+user.id+",\n    	\"login\": \""+user.login+"\",\n    	\"password\": \""+user.password+"\",\n    	\"name\": \""+user.name+"\",\n    	\"email\": \""+user.email+"\",\n    	\"about\": \""+user.about+"\",\n    	\"usersType\": \"M\"\n    }\n}"
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

    private fun regMusGenre(musId:Int,user: UserModel,GenMusId:Int,GenId:Int,GenName:String) {
        val url = URL("http://10.0.2.2:8081/musician_genres/create-musician_genre")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"musician\": {\n        \"musicianId\": "+musId+",\n        \"users\": {\n            \"usersId\": "+user.id+",\n            \"login\": \""+user.login+"\",\n            \"password\": \""+user.password+"\",\n            \"name\": \""+user.name+"\",\n            \"email\": \""+user.email+"\",\n            \"about\": \""+user.about+"\",\n            \"usersType\": \"M\"\n        }\n    },\n    \"genres\": {\n        \"genreId\": "+GenId+",\n        \"genreName\": \""+GenName+"\"\n    },\n    \"musiciangenresId\": "+GenMusId+"\n}"
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
    private fun regMusInstrument(musId:Int,user: UserModel,InstMusId:Int,InstId:Int,InstName:String) {
        val url = URL("http://10.0.2.2:8081/musician_instruments/create-musician_instrument")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.doOutput = true
        val data =
            "{\n    \"musicianInstrumentsId\": "+InstMusId+",\n    \"musician\": {\n        \"musicianId\": "+musId+",\n        \"users\": {\n            \"usersId\": "+user.id+",\n            \"login\": \""+user.login+"\",\n            \"password\": \""+user.password+"\",\n            \"name\": \""+user.name+"\",\n            \"email\": \""+user.email+"\",\n            \"about\": \""+user.about+"\",\n            \"usersType\": \"M\"\n        }\n    },\n    \"instruments\": {\n        \"instId\": "+InstId+",\n        \"instName\": \""+InstName+"\"\n    }\n}"
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