package com.example.wsprog

import android.app.VoiceInteractor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.wsprog.network.Net
import kotlinx.android.synthetic.main.activity_reg.*
import org.json.JSONObject

class RegActivity : AppCompatActivity() {

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        requestQueue = Volley.newRequestQueue(this)

        buttonER.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRR.setOnClickListener{
            val name = Name.text.toString()
            val lname = Lname.text.toString()
            val Remail = emailR.text.toString()
            val passw = pass.text.toString()
            val reppas = passr.text.toString()

            if (name == "" || lname == "" || Remail == "" || passw == "" || reppas == ""){
                Toast.makeText(this, "Field not right", Toast.LENGTH_SHORT).show()
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(Remail).matches()){
                Toast.makeText(this, "Email ERROR", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val requestJson = JSONObject()
                            .put("email", Remail)
                            .put("password", passw)
                            .put("firstName", name)
                            .put("lastName", lname)

            val request = JsonObjectRequest(
                    Request.Method.POST,
                    Net.register_URL,
                    requestJson,

                    { response ->
                        Toast.makeText(this, "token - ${response["token"]}", Toast.LENGTH_SHORT).show()
                    },

                    {
                        Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
                        it.printStackTrace()
                    }
            )

            requestQueue.add(request)
        }
    }

}