package com.example.reinoalimentario

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespuestaIncorrectaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respuesta_incorrecta)

        val explanationText: TextView = findViewById(R.id.explicacion)
        val retryButton: Button = findViewById(R.id.button_volver)

        val explanation = intent.getStringExtra("EXPLICACION")
        explanationText.text = explanation

        retryButton.setOnClickListener {
            finish() // Volver a la pregunta actual
        }
    }
}