package com.udea.sebastian.osorios.sedesudea

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        val btnContinue : Button = findViewById(R.id.btn_continue)

        btnContinue.setOnClickListener {
            var intent = Intent(this,MainActivity ::class.java)
            startActivity(intent)
        }

    }
}
