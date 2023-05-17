package com.potemkin.musiciansocialmobileapp.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.potemkin.musiciansocialmobileapp.LoginActivity
import com.potemkin.musiciansocialmobileapp.R
import com.potemkin.musiciansocialmobileapp.UserPageActivity
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val items = listOf("Музыкант","Администратор базы","Администратор площадки")

        val autoComplete : AutoCompleteTextView = findViewById(R.id.autoComplete)

        val adapter = ArrayAdapter(this,R.layout.list_item,items)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->

            val itemSelected = adapterView.getItemAtPosition(i)
            Toast.makeText(this,"Item: " +itemSelected,Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickLogin(view: View) {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}