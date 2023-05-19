package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.potemkin.musiciansocialmobileapp.*
import androidx.appcompat.app.AppCompatActivity
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
        val cvImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_conven)
        val rbImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_repbase)


        val RepBasePoint = Point(55.788089, 37.705623)
        val repbase = RepBaseModel("KVLT","КУЛЬТ - настоящий храм творчества и оплот музыкальной КУЛЬТуры, созданный музыкантами для музыкантов.\n" +
                "Каждая наша комната имеет собственные характер и звук.","Москва, Электрозаводская улица, 21")
        mapview.map.mapObjects.addPlacemark(RepBasePoint,rbImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo(repbase.RepBaseName, repbase.RepBaseAbout, repbase.RepBaseAddress)
            true
        }


        val ConVenPoint = Point(55.791567, 37.682718)
        val conven = ConVenueModel("Potemkin Club","Площадка в комнате Саши","2-ой Полевой Переулок")
        mapview.map.mapObjects.addPlacemark(ConVenPoint,cvImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo(conven.ConVenName,conven.ConVenAbout,conven.ConVenAddress)
            true
        }


    }


    private fun showPlaceInfo(name: String, about: String, address: String) {
        placeInfo.visibility = View.VISIBLE
        place_name_tv.text=name
        place_about_tv.text=about
        place_address_tv.text=address
        closeInfoButton.setOnClickListener { placeInfo.visibility = View.GONE }
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


    fun getAllRepBases(){

    }

    fun getAllConVenues(){

    }
}