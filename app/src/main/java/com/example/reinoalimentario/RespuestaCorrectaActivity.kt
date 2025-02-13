package com.example.reinoalimentario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RespuestaCorrectaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.respuesta_correcta)

        val explanationText: TextView = findViewById(R.id.explicacion)
        val nextButton: Button = findViewById(R.id.button_siguiente)

        val explanation = intent.getStringExtra("EXPLICACION")
        val hasNext = intent.getBooleanExtra("SIGUIENTE", false)
        val nextIndex = intent.getIntExtra("QUESTION_INDEX", 0)

        explanationText.text = explanation

        nextButton.setOnClickListener {
            val intent = Intent(this, Quecomelanimal::class.java)
            intent.putExtra("QUESTION_INDEX", nextIndex) // Enviar el nuevo índice
            startActivity(intent)
            finish()
        }

        if (!hasNext) {
            nextButton.text = "Finalizar"
            nextButton.setOnClickListener { finish() }
        }
    }
}