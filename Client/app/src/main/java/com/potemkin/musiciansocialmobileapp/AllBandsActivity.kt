package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.models.BandModel
import com.potemkin.musiciansocialmobileapp.models.MusicianModel
import kotlinx.android.synthetic.main.activity_all_bands.*
import org.json.JSONArray
import org.json.JSONObject

class AllBandsActivity : AppCompatActivity(), BandAdapter.OnItemClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_bands)
        val url = "http://10.0.2.2:8081/band/all-bands"
        val itemList: ArrayList<BandModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val name = jsonObj.getString("bandName")
                val id = jsonObj.getString("bandId")
                val genre: JSONObject = jsonObj.getJSONObject("genres")
                val genreName = genre.getString("genreName")
                val itemsDetails = BandModel(name,genreName)

                itemList.add(itemsDetails)
                rvBands.layoutManager = LinearLayoutManager(this)
                val itemAdapter = BandAdapter(this, itemList, this)
                rvBands.adapter = itemAdapter
            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)



    }

    override fun onItemClick(item: ArrayList<BandModel>, position: Int) {
        val i = Intent(this, BandPageActivity::class.java).apply {
            putExtra("name", item[position].name)
        }
        startActivity(i)

    }
    fun ProfileClick(view: View) {
        val i = Intent(this, ProfilePageActivity::class.java)
        startActivity(i)
    }
    fun ChatClick(view: View) {
        val i = Intent(this, ChatActivity::class.java)
        startActivity(i)
    }
    fun MapClick(view: View) {
        val i = Intent(this, MapActivity::class.java)
        startActivity(i)
    }
    fun AllMusicianClick(view: View) {
        val i = Intent(this, AllMusicianActivity::class.java)
        startActivity(i)
    }
    fun getJson(): ArrayList<BandModel> {
        val url = "http://localhost:8081/musician/all-musicians"
        val itemList: ArrayList<BandModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj: JSONObject = JSONObject(data)
            val itemsArray: JSONArray = jsonObj.getJSONArray("users")

            for (i in 0 .. itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)

                val name = item.getString("name")

                val itemsDetails = BandModel(name, "icon_url")

                itemList.add(itemsDetails)

            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        return itemList
    }
}