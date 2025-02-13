package com.example.reinoalimentario

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity

class Trivia : ComponentActivity() {
    private val animales = listOf(
        Triple("León", "Come carne, caza cebras y antílopes", R.drawable.leon),
        Triple("Tigre", "Come ciervos, búfalos y jabalíes", R.drawable.tigre),
        Triple("Vaca", "Se alimenta de pasto, heno y alimentos concentrados", R.drawable.vaca),
        Triple("Conejo", "Se alimenta heno, verduras frescas y frutas", R.drawable.conejo),
    )
    private var indiceActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trivia_animal)

        val imagenAnimal = findViewById<ImageView>(R.id.imagenAnimal)
        val nombreAnimal = findViewById<TextView>(R.id.nombreAnimal)
        val grupoOpciones = findViewById<RadioGroup>(R.id.grupoOpciones)
        val botonVerificar = findViewById<Button>(R.id.botonVerificar)
        val botonSiguiente = findViewById<Button>(R.id.botonSiguiente)
        val resultadoTexto = findViewById<TextView>(R.id.resultadoTexto)

        // Mostrar el primer animal
        actualizarAnimal(imagenAnimal, nombreAnimal, grupoOpciones)

        botonVerificar.setOnClickListener {
            val opcionSeleccionadaId = grupoOpciones.checkedRadioButtonId
            if (opcionSeleccionadaId != -1) {
                val opcionSeleccionada = findViewById<RadioButton>(opcionSeleccionadaId).text.toString()
                val respuestaCorrecta = animales[indiceActual].second

                if (opcionSeleccionada == respuestaCorrecta) {
                    resultadoTexto.text = "✅ ¡Correcto!"
                } else {
                    resultadoTexto.text = "❌ Incorrecto. Respuesta correcta: $respuestaCorrecta"
                }
            } else {
                resultadoTexto.text = "Selecione una opción antes de verificar."
            }
        }

        botonSiguiente.setOnClickListener {
            indiceActual = (indiceActual + 1) % animales.size
            actualizarAnimal(imagenAnimal, nombreAnimal, grupoOpciones)
            resultadoTexto.text = ""
        }
    }

    private fun actualizarAnimal(imagen: ImageView, nombre: TextView, grupoOpciones: RadioGroup) {
        val (nombreAnimal, _, imagenId) = animales[indiceActual]
        imagen.setImageResource(imagenId)
        nombre.text = nombreAnimal

        // Limpiar opciones anteriores
        grupoOpciones.clearCheck()
        grupoOpciones.removeAllViews()

        // Agregar nuevas opciones
        val opciones = listOf(
            "Come carne, caza cebras y antílopes",
            "Come ciervos, búfalos y jabalíes",
            "Se alimenta de pasto, heno y alimentos concentrados",
            "Se alimenta heno, verduras frescas y frutas"
        ).shuffled()

        opciones.forEach { opcion ->
            val radioButton = RadioButton(this)
            radioButton.text = opcion
            grupoOpciones.addView(radioButton)
        }

    }
}