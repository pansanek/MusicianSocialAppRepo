package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.potemkin.musiciansocialmobileapp.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.models.*
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MapActivity : AppCompatActivity() {
    lateinit var mapview:MapView
    private var markerTapListener: MapObjectTapListener? = null
    private var placeList = ArrayList<PlacemarkMapObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("cb7bdd48-6aa7-4812-ba6e-72902c458a81")
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        placeInfo.visibility = View.GONE
        mapview = findViewById(R.id.mapView)
        mapview.map.move(CameraPosition(Point(55.754598, 37.619703),11.0f,0.0f,0.0f),
        Animation(Animation.Type.SMOOTH,0f),null)


        val urlRep = "http://10.0.2.2:8081/rep_base/all-rep_bases"
        val queueRep = Volley.newRequestQueue(this)

        val RepRequest = StringRequest(
            Request.Method.GET, urlRep,
            { response ->
                val data = response.toString()
                System.out.println("Response is: "+data)
                val jsonArray = JSONArray(data)

                for (i in 0 until jsonArray.length()) {
                    val items = jsonArray.getJSONObject(i)
                    val about = items.getString("repBaseAbout")
                    val address = items.getString("repBaseAddress")
                    val name = items.getString("repBaseName")
                    makeRepPoint(name,about,address)

                }
            },
            { System.out.println("Response is: Sorry :(") })

        queueRep.add(RepRequest)

        val urlCon = "http://10.0.2.2:8081/con_venue/all-con_venue"
        val queueCon = Volley.newRequestQueue(this)

        val ConRequest = StringRequest(
            Request.Method.GET, urlCon,
            { response ->
                val data = response.toString()
                System.out.println("Response is: "+data)
                val jsonArray = JSONArray(data)

                for (i in 0 until jsonArray.length()) {
                    val items = jsonArray.getJSONObject(i)
                    val about = items.getString("conVenAbout")
                    val address = items.getString("conVenAddress")
                    val name = items.getString("conVenName")
                    makeConPoint(name,about,address)

                }
            },
            { System.out.println("Response is: Sorry :(") })

        queueCon.add(ConRequest)

    }


    private fun showPlaceInfo(name: String, about: String, address: String) {
        placeInfo.visibility = View.VISIBLE
        place_name_tv.text=name
        place_about_tv.text=about
        place_address_tv.text=address
        closeInfoButton.setOnClickListener { placeInfo.visibility = View.GONE }
    }

    fun makeRepPoint(name: String, about: String, address: String){
        val rbImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_repbase)
        System.out.println("Addr"+address)
        var myAddr:AddressModel = getLatLngFromAddress(this,address)
        val Point = Point(myAddr.Latitude, myAddr.Longitude)
        System.out.println("Lat is"+myAddr.Latitude+" Long is"+myAddr.Longitude)
        mapview.map.mapObjects.addPlacemark(Point,rbImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo(name,about,address)

            true
        }
    }

    fun makeConPoint(name: String, about: String, address: String){
        val rbImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_conven)
        System.out.println("Addr"+address)
        var myAddr:AddressModel = getLatLngFromAddress(this,address)
        val Point = Point(myAddr.Latitude, myAddr.Longitude)
        System.out.println("Lat is"+myAddr.Latitude+" Long is"+myAddr.Longitude)
        mapview.map.mapObjects.addPlacemark(Point,rbImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo(name,about,address)

            true
        }
    }

    override fun onStart() {
        mapview.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }

    override fun onStop() {
        mapview.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
    fun ProfileClick(view: View) {
        val i = Intent(this, UserPageActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid())
    }
    fun ChatClick(view: View) {
        val i = Intent(this, ChatActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid())
    }
    fun AllMusicianClick(view: View) {
        val i = Intent(this, AllMusicianActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    fun AllBandClick(view: View) {
        val i = Intent(this, AllBandsActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid())
    }

    fun getLatLngFromAddress(context: Context, mAddress: String): AddressModel {
        val coder = Geocoder(context)
        lateinit var address: List<Address>
        try {
            address = coder.getFromLocationName(mAddress, 5) as List<Address>
            if (address == null) {
                Toast.makeText(
                    this,
                    "Fail to find Lat,Lng",
                    Toast.LENGTH_LONG
                ).show()
            }
            val location = address[0]
            return AddressModel(location.latitude, location.longitude)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Fail: "+e.toString(),
                Toast.LENGTH_LONG
            ).show()
            return AddressModel(0.0,0.0)
        }
    }

}