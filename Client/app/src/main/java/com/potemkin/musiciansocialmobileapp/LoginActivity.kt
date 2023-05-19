package com.potemkin.musiciansocialmobileapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.potemkin.musiciansocialmobileapp.models.UserModel
import com.potemkin.musiciansocialmobileapp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharePreference = getSharedPreferences("MYSHAR",Context.MODE_PRIVATE)
        val getEmail = sharePreference.getString("EMAIL","")
        val getPass = sharePreference.getString("PASS","")

        if(getEmail!=""&&getPass!=""){
            val i = Intent(this@LoginActivity, MapActivity::class.java)
            startActivity(i)
        }

        val items: ArrayList<UserModel> = getJson()
        //Toast.makeText(this, "Ошибка"+ items[1], Toast.LENGTH_SHORT).show()
        loginButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                var editTextEmail = editTextEmail.text.toString()
                var editTextPassword = editTextPassword.text.toString()
                var user = UserModel(editTextEmail,editTextPassword,"","","")
                //justfortest
                var test = UserModel("nocturnal@ex.com","12341234","NikNoc","Nik","")
                //for(i in 0 .. items.size){
                    if(user.email == test.email && user.password == test.password){
                        val editor = sharePreference.edit()

                        editor.putString("EMAIL",editTextEmail)
                        editor.putString("PASS",editTextPassword)
                        editor.apply()
                        editor.commit()
                        val i = Intent(this@LoginActivity, MapActivity::class.java)
                        startActivity(i)
                    }
                else Toast.makeText(this@LoginActivity, "Такого пользователя не существует", Toast.LENGTH_SHORT).show()

                //}
            }

        })
    }



    fun onClickReg(view: View) {
        val i = Intent(this, RegistrationActivity::class.java)
        startActivity(i)
    }

    fun getJson(): ArrayList<UserModel> {
        val url = "http://localhost:8081/musician/all-musicians"
        val itemList: ArrayList<UserModel> = ArrayList()
        val queue = Volley.newRequestQueue(this)
        val req = StringRequest(Request.Method.GET, url, { response ->
            val data = response.toString()
            val jsonObj: JSONObject = JSONObject(data)
            val itemsArray: JSONArray = jsonObj.getJSONArray("users")

            for (i in 0 .. itemsArray.length()) {
                val item = itemsArray.getJSONObject(i)

                val email = item.getString("email")
                val password = item.getString("password")

                val itemsDetails = UserModel(email,password,"","","")

                itemList.add(itemsDetails)

            }

        }, {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show() }
        )
        queue.add(req)

        return itemList
    }
}