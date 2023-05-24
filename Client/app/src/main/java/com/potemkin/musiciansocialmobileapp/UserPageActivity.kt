package com.potemkin.musiciansocialmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.models.MusicianModel
import kotlinx.android.synthetic.main.activity_all_musician.*
import kotlinx.android.synthetic.main.activity_user_page.*
import org.json.JSONArray
import org.json.JSONObject

class UserPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)
        idTv.text = "#" + intent.getIntExtra("id",0)
        lg_tv.text = intent.getStringExtra("login")
        nameTv.text = intent.getStringExtra("name")
        aboutTv.text = intent.getStringExtra("about")
        instrument.text = "Instrument: " + intent.getStringExtra("instrument")
        val url = "http://10.0.2.2:8081/musician_genres/all-musician_genres"
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonArray = JSONArray(data)
            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val musObj: JSONObject = jsonObj.getJSONObject("musician")
                val items: JSONObject = musObj.getJSONObject("users")
                val id = items.getInt("usersId")
                if(id==intent.getIntExtra("id",0)) {
                    val genObj: JSONObject = jsonObj.getJSONObject("genres")
                    val genre = genObj.getString("genreName")
                    genreTv.text = "Genre: " +genre.toString()
                }
            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        getBackButton.setOnClickListener {
            finish()
            System.out.close()
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

    fun ProfileClick(view: View) {
        val i = Intent(this, ProfilePageActivity::class.java)
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
}