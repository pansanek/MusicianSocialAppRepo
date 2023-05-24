package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.models.MusicianModel
import kotlinx.android.synthetic.main.activity_all_musician.*
import org.json.JSONArray
import org.json.JSONObject


class AllMusicianActivity : AppCompatActivity(), MusicianAdapter.OnItemClickListener {
    val url = "http://10.0.2.2:8081/musician/all-musicians"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_musician)
        val url = "http://10.0.2.2:8081/musician/all-musicians"
        val itemList: ArrayList<MusicianModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val items: JSONObject = jsonObj.getJSONObject("users")

                val name = items.getString("login")
                val id = items.getInt("usersId")
                val itemsDetails = MusicianModel(name,id,name,name)

                itemList.add(itemsDetails)
                rvList.layoutManager = LinearLayoutManager(this)
                val itemAdapter = MusicianAdapter(this, itemList, this)
                rvList.adapter = itemAdapter
            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)


    }

    override fun onItemClick(item: ArrayList<MusicianModel>, position: Int) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        val i = Intent(this, UserPageActivity::class.java).apply {
            putExtra("id", item[position].userId)
            putExtra("name", item[position].name)
            putExtra("genre", item[position].genres)
            putExtra("instrument", item[position].instruments)
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
    fun AllBandClick(view: View) {
        val i = Intent(this, AllBandsActivity::class.java)
        startActivity(i)
    }


    fun getJson(): ArrayList<MusicianModel> {
        val url = "http://10.0.2.2:8081/musician/all-musicians"
        val itemList: ArrayList<MusicianModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val items: JSONObject = jsonObj.getJSONObject("users")

                val name = items.getString("name")
                val id = items.getInt("usersId")

                val itemsDetails = MusicianModel(name,id,name,name)

                itemList.add(itemsDetails)

            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        return itemList
    }
}