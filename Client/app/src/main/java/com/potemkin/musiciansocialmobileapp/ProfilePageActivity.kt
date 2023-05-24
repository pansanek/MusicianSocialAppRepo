package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_profile_page.*
import org.json.JSONArray
import org.json.JSONObject

class ProfilePageActivity : AppCompatActivity() {
    var userBand:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        val sharePreference = getSharedPreferences("MYSHAR", Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("EMAIL","")
        profile_tv.text = getEmail
        //http://localhost:8081/users/user-by-email?email=nocturnal@ex.com
        val url = "http://10.0.2.2:8081/users/user-by-email?email="+getEmail
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj = JSONObject(data)
            val login = jsonObj.getString("login")
            val name = jsonObj.getString("name")
            val id = jsonObj.getInt("usersId")
            val about = jsonObj.getString("about")
            val userType = jsonObj.getString("usersType")
            profile_tv.text = login
            nameTv.text =name
            idTv.text = "#"+id.toString()
            profile_aboutTv.text = about
            when (userType) {
                "M" -> {
                    myTv.text = "Моя группа"
                    val AddQueue = Volley.newRequestQueue(this)
                    val GenUrl = "http://10.0.2.2:8081/musician_genres/all-musician_genres"
                    val GenReq = StringRequest(Request.Method.GET, GenUrl, { response ->
                        val GenData = response.toString()
                        val GenJsonArray = JSONArray(GenData)

                        for (i in 0 until GenJsonArray.length()) {
                            val GenJsonObj = GenJsonArray.getJSONObject(i)
                            val GenMusObj: JSONObject = GenJsonObj.getJSONObject("musician")
                            val GenItems: JSONObject = GenMusObj.getJSONObject("users")
                            val idG = GenItems.getInt("usersId")
                            if(idG==id) {
                                val genObj: JSONObject = GenJsonObj.getJSONObject("genres")
                                val genre = genObj.getString("genreName")
                                genreTv.text = "Genre: " +genre
                            }
                        }

                    }, {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    val InstUrl = "http://10.0.2.2:8081/musician_instruments/all-musician_instruments"
                    val InstReq = StringRequest(Request.Method.GET, InstUrl, { response ->
                        val InstData = response.toString()
                        val InstJsonArray = JSONArray(InstData)

                        for (i in 0 until InstJsonArray.length()) {
                            val InstJsonObj = InstJsonArray.getJSONObject(i)
                            val InstMusObj: JSONObject = InstJsonObj.getJSONObject("musician")
                            val InstItems: JSONObject = InstMusObj.getJSONObject("users")
                            val idI = InstItems.getInt("usersId")
                            if(idI==id) {
                                val InstObj: JSONObject = InstJsonObj.getJSONObject("instruments")
                                val Instrument = InstObj.getString("instName")
                                instrument.text = "Instrument: " +Instrument
                            }
                        }

                    }, {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    val BandUrl = "http://10.0.2.2:8081/musician_bands/all-musician_bands"
                    val BandReq = StringRequest(Request.Method.GET, BandUrl, { response ->
                        val BandData = response.toString()
                        val BandJsonArray = JSONArray(BandData)

                        for (i in 0 until BandJsonArray.length()) {
                            val BandJsonObj = BandJsonArray.getJSONObject(i)
                            val BandMusObj: JSONObject = BandJsonObj.getJSONObject("musician")
                            val BandItems: JSONObject = BandMusObj.getJSONObject("users")
                            val idB = BandItems.getInt("usersId")
                            if(idB==id) {
                                val BandObj: JSONObject = BandJsonObj.getJSONObject("bands")
                                val band = BandObj.getString("bandName")
                                bandTv.text = "Band: " +band
                                userBand=band
                            }
                        }

                    }, {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
                    )
                    AddQueue.add(GenReq)
                    AddQueue.add(InstReq)
                    AddQueue.add(BandReq)

                }
                "B" -> myTv.text = "Моя база"
                else -> {
                    myTv.text = "Моя площадка"

                }
            }
        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )

        queue.add(req)


        myBandButton.setOnClickListener { val i = Intent(this, BandPageActivity::class.java).apply {
            putExtra("name", userBand)
        }
            startActivity(i)
        }
    }
    fun ChatClick(view: View) {
        val i = Intent(this, ChatActivity::class.java)
        startActivity(i)
    }

    fun AllMusicianClick(view: View) {
        val i = Intent(this, AllMusicianActivity::class.java)
        startActivity(i)
    }

    fun MapClick(view: View) {
        val i = Intent(this, MapActivity::class.java)
        startActivity(i)
    }

    fun AllBandClick(view: View) {
        val i = Intent(this, AllBandsActivity::class.java)
        startActivity(i)
    }

    fun EditProfileClick(view: View) {
        Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show()
    }
    fun MyBandClick(view: View) {
        val i = Intent(this, BandPageActivity::class.java)
        startActivity(i)
    }
}