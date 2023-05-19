package com.potemkin.musiciansocialmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_user_page.*

class UserPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_page)
        idTv.text = "#" + intent.getIntExtra("id",0)
        lg_tv.text = intent.getStringExtra("name")
        aboutTv.text = intent.getStringExtra("about")
        instrument.text = "Instrument: " + intent.getStringExtra("instrument")
        genres.text = "Genres: " +intent.getStringExtra("genre")
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

    fun ProfileClick(view: View) {
        val i = Intent(this, ProfilePageActivity::class.java)
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