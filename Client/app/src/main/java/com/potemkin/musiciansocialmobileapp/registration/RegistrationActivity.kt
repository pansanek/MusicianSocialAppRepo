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
import com.potemkin.musiciansocialmobileapp.models.UserModel
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    var itemSelected:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val items = listOf("Музыкант","Администратор базы","Администратор площадки")

        val autoComplete : AutoCompleteTextView = findViewById(R.id.autoComplete)

        val adapter = ArrayAdapter(this,R.layout.list_item,items)

        autoComplete.setAdapter(adapter)

        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
            adapterView, view, i, l ->

            itemSelected = adapterView.getItemAtPosition(i).toString()
        }
        regButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var editTextEmail = regEditTextEmailAddress.text.toString()
                var editTextPassword = regEditTextPassword.text.toString()
                var editTextLogin = regEditTextLogin.text.toString()
                var editTextPersonName = regEditTextPersonName.text.toString()
                var user = UserModel(editTextEmail,editTextPassword,editTextLogin,editTextPersonName,"")
                //justfortest
                when(itemSelected){
                    "Музыкант"->{
                        val i = Intent(this@RegistrationActivity, MusRegistrationActivity::class.java).apply {

                            putExtra("email", editTextEmail)
                            putExtra("password", editTextPassword)
                            putExtra("login", editTextLogin)
                            putExtra("name", editTextPersonName)
                            putExtra("type", "M")
                        }

                        startActivity(i)
                    }
                    "Администратор базы"->{
                        val i = Intent(this@RegistrationActivity, PlaceRegistrationActivity::class.java).apply {

                            putExtra("email", editTextEmail)
                            putExtra("password", editTextPassword)
                            putExtra("login", editTextLogin)
                            putExtra("name", editTextPersonName)
                            putExtra("type", "R")
                        }

                        startActivity(i)
                    }
                    "Администратор площадки"->{
                        val i = Intent(this@RegistrationActivity, PlaceRegistrationActivity::class.java).apply {

                            putExtra("email", editTextEmail)
                            putExtra("password", editTextPassword)
                            putExtra("login", editTextLogin)
                            putExtra("name", editTextPersonName)
                            putExtra("type", "C")
                        }

                        startActivity(i)
                    }
                }

            }

        })
    }

    fun onClickLogin(view: View) {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}