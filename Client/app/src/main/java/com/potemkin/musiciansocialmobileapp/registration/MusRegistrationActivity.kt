package com.potemkin.musiciansocialmobileapp.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.potemkin.musiciansocialmobileapp.R

class MusRegistrationActivity : AppCompatActivity() {
    var genreSelected:String = ""
    var instrumentSelected:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mus_registration)

        val genres = listOf("Metalcore","Deathcore","Hardcore","Pop-punk","Punk","Heavy metal")
        val instruments = listOf("Guitar","Drums","Bass","Vocals")

        val autoCompleteGenre : AutoCompleteTextView = findViewById(R.id.autoCompleteGenre)
        val autoCompleteInstrument : AutoCompleteTextView = findViewById(R.id.autoCompleteInstrument)

        val adapterGenres = ArrayAdapter(this,R.layout.list_item,genres)
        val adapterInstruments = ArrayAdapter(this,R.layout.list_item,instruments)

        autoCompleteGenre.setAdapter(adapterGenres)
        autoCompleteInstrument.setAdapter(adapterInstruments)

        autoCompleteGenre.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            genreSelected = adapterView.getItemAtPosition(i).toString()
        }

        autoCompleteInstrument.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->

            instrumentSelected = adapterView.getItemAtPosition(i).toString()
        }


    }
}