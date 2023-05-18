package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
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
    val yotcLat = 55.788089
    val yotcLon = 37.705623
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
        val RepBasePoint = Point(yotcLat, yotcLon)
        val rbImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_rep_base)
        mapview.map.mapObjects.addPlacemark(RepBasePoint,rbImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo()


            true
        }
        val ConVenPoint = Point(55.791567, 37.682718)
        val cvImageProvider: ImageProvider = ImageProvider
            .fromResource(
                this,
                R.drawable.ic_con_venue)
        mapview.map.mapObjects.addPlacemark(ConVenPoint,cvImageProvider).addTapListener { mapObject, point ->
            showPlaceInfo()


            true
        }


    }

    private fun showPlaceInfo() {
        placeInfo.visibility = View.VISIBLE

        closeButton.setOnClickListener { placeInfo.visibility = View.GONE }
    }

    private fun setTapListener(listener: MapObjectTapListener) {
        markerTapListener = listener
    }

    /*
        Это обёртка над addPlacemark
        в которой к маркеру цепляется обработчик событий
        и полезная нагрузка
    */
    private fun addMarker(
        latitude: Double,
        longitude: Double,
        @DrawableRes imageRes: Int,
        userData: Any? = null
    ): PlacemarkMapObject
    {
        val marker = mapview.map.mapObjects
            .addPlacemark(
                Point(latitude, longitude),
                ImageProvider
                    .fromResource(
                        this,
                        imageRes)
            )
        marker.userData = userData
        markerTapListener?.let{
            marker
                .addTapListener(it) }
        return marker
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
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    fun ChatClick(view: View) {
        val i = Intent(this, ChatActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    fun AllMusicianClick(view: View) {
        val i = Intent(this, AllMusicianActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    fun AllBandClick(view: View) {
        val i = Intent(this, AllBandsActivity::class.java)
        startActivity(i)
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    fun getAllRepBases(){

    }

    fun getAllConVenues(){

    }
}