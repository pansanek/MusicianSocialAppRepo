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
import kotlinx.android.synthetic.main.activity_all_bands.*
import org.json.JSONArray
import org.json.JSONObject

class AllBandsActivity : AppCompatActivity(), BandAdapter.OnItemClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_bands)
        var test1 = BandModel("Cohen_noise","1")
        var test2 = BandModel("Knocked Loose","2")
        val items: ArrayList<BandModel> = ArrayList()
        items.add(test1)
        items.add(test2)

        rvBands.layoutManager = LinearLayoutManager(this)

        val itemAdapter = BandAdapter(this, items, this)

        rvBands.adapter = itemAdapter

    }

    override fun onItemClick(item: ArrayList<BandModel>, position: Int) {
        val i = Intent(this, BandPageActivity::class.java).apply {

            putExtra("image", item[position].icon_url)
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