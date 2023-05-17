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
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.android.synthetic.main.activity_all_musician.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList


class AllMusicianActivity : AppCompatActivity(), MusicianAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_musician)
        var test1 = MusicianModel("1","Саша","Nu-Metalcore","Drums")
        var test2 = MusicianModel("2","Ник","Metalcore","Guitar")
        val items: ArrayList<MusicianModel> = ArrayList()
        items.add(test1)
        items.add(test2)

        rvList.layoutManager = LinearLayoutManager(this)

        val itemAdapter = MusicianAdapter(this, items, this)

        rvList.adapter = itemAdapter

    }

    override fun onItemClick(item: ArrayList<MusicianModel>, position: Int) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show()
        val i = Intent(this, UserPageActivity::class.java).apply {

            putExtra("image", item[position].icon_url)
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

    fun getJson(): ArrayList<UserModel> {
        val url = "http://localhost:8081/musician/all-musicians"
        val itemList: ArrayList<UserModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj: JSONObject = JSONObject(data)
            val itemsArray: JSONArray = jsonObj.getJSONArray("users")

            for (i in 0 .. itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)

                val email = item.getString("email")
                val password = item.getString("password")

                val itemsDetails = UserModel(email,password,"","")

                itemList.add(itemsDetails)

            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        return itemList
    }
}