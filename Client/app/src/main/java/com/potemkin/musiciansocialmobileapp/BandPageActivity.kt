package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_band_page.*
import kotlinx.android.synthetic.main.activity_band_page.getBackButton
import kotlinx.android.synthetic.main.activity_band_page.lg_tv
import kotlinx.android.synthetic.main.activity_user_page.*

class BandPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_band_page)
        lg_tv.text = intent.getStringExtra("name")
        genresTv.text = "Genres: " +intent.getStringExtra("genre")
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

    fun MapClick(view: View) {
        val i = Intent(this, MapActivity::class.java)
        startActivity(i)
    }

    fun AllBandClick(view: View) {
        val i = Intent(this, AllBandsActivity::class.java)
        startActivity(i)
    }
    fun ProfileClick(view: View) {
        val i = Intent(this, ProfilePageActivity::class.java)
        startActivity(i)
    }
}