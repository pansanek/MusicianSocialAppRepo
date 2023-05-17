package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapActivity : AppCompatActivity() {
    lateinit var mapview:MapView
    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("cb7bdd48-6aa7-4812-ba6e-72902c458a81")
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapview = findViewById(R.id.mapView)
        mapview.map.move(CameraPosition(Point(55.754598, 37.619703),11.0f,0.0f,0.0f),
        Animation(Animation.Type.SMOOTH,1f),null)

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