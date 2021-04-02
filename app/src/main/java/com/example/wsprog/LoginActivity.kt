package com.example.wsprog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        requestQueue = Volley.newRequestQueue(this)

        buttonRL.setOnClickListener{
            val intent = Intent(this, RegActivity::class.java)
            startActivity(intent)
        }

        buttonEL.setOnClickListener{
            val email =  emailL.text.toString()
            val password = passwoordL.text.toString()

            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Field not right", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val request = JsonObjectRequest(
                    Request.Method.POST,
                    "http://192.168.43.158/auth/login",
                    JSONObject()
                            .put("email", email)
                            .put("password", password),

                    {
                        Toast.makeText(this, "Ок", Toast.LENGTH_SHORT).show()
                    },

                    {
                        Toast.makeText(this, "Не ок", Toast.LENGTH_SHORT).show()
                    }
            )
        }
    }
}