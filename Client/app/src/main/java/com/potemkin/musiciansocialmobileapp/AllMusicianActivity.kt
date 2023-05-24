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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_musician)

        val url = "http://10.0.2.2:8081/musician_instruments/all-musician_instruments"
        val itemList: ArrayList<MusicianModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonArray = JSONArray(data)

            for (i in 0 until jsonArray.length()) {
                val jsonObj = jsonArray.getJSONObject(i)
                val musObj: JSONObject = jsonObj.getJSONObject("musician")
                val items: JSONObject = musObj.getJSONObject("users")
                val login = items.getString("login")
                val name = items.getString("name")
                val id = items.getInt("usersId")
                val about = items.getString("about")
                val instObj: JSONObject = jsonObj.getJSONObject("instruments")
                val inst = instObj.getString("instName")
                val itemsDetails = MusicianModel(login,id,name,inst,about)

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
            putExtra("login", item[position].login)
            putExtra("about", item[position].about)
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



}