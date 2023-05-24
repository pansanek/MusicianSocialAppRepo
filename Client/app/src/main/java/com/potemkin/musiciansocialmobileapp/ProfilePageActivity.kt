package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile_page.*

class ProfilePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)
        val sharePreference = getSharedPreferences("MYSHAR", Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("EMAIL","")
        profile_tv.text = getEmail
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

    fun EditProfileClick(view: View) {
        Toast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show()
    }
    fun MyBandClick(view: View) {
        val i = Intent(this, BandPageActivity::class.java)
        startActivity(i)
    }
}