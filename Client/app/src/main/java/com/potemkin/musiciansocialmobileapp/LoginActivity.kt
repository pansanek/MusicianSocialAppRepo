package com.potemkin.musiciansocialmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.potemkin.musiciansocialmobileapp.registration.RegistrationActivity
import java.util.ArrayList

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val url = "http://localhost:8081/musician/all-musicians"
        val itemList: ArrayList<MusicianModel> = ArrayList()
    }

    fun onClickReg(view: View) {
        val i = Intent(this, RegistrationActivity::class.java)
        startActivity(i)
    }

}