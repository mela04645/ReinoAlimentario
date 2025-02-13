package com.example.reinoalimentario

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Quecomelanimal : AppCompatActivity() {

    private lateinit var animalImage: ImageView
    private lateinit var questionText: TextView
    private lateinit var foodOption1: ImageView
    private lateinit var foodOption2: ImageView
    private lateinit var foodOption3: ImageView
    private lateinit var foodOption4: ImageView

    private val questions: List<Triple<Int, String, List<Pair<Int, String>>>> = listOf(
        Triple(R.drawable.leon, "¿Qué come este animal?", listOf(
            Pair(R.drawable.carne, "El león es un carnívoro y se alimenta principalmente de carne."),
            Pair(R.drawable.hierba, "El león no come hierba, es un carnívoro."),
            Pair(R.drawable.frutas, "El león no come frutas, es un carnívoro."),
            Pair(R.drawable.pescado, "El león no suele comer pescado, prefiere carne de mamíferos."))
        ),
        Triple(R.drawable.conejo, "¿Qué come este animal?", listOf(
            Pair(R.drawable.zanahoria, "El conejo es herbívoro y se alimenta de zanahorias y otras plantas."),
            Pair(R.drawable.carne, "El conejo no come carne, es herbívoro."),
            Pair(R.drawable.pescado, "El conejo no come pescado, es herbívoro."),
            Pair(R.drawable.queso, "El conejo no come queso, es herbívoro."))
        ),
        Triple(R.drawable.elefante, "¿Qué come este animal?", listOf(
            Pair(R.drawable.hojas, "El elefante es herbívoro y se alimenta de hojas y ramas."),
            Pair(R.drawable.huevos, "El elefante no come huevos, es herbívoro."),
            Pair(R.drawable.queso, "El elefante no come queso, es herbívoro."),
            Pair(R.drawable.nueces, "El elefante no suele comer nueces, prefiere hojas y ramas."))
        )
    )

    private var currentQuestionIndex = 0
    private val correctAnswers = listOf(0, 0, 0) // Índice de las respuestas correctas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.que_come_el_animal)

        animalImage = findViewById(R.id.imagenAnimal)
        questionText = findViewById(R.id.pregunta)
        foodOption1 = findViewById(R.id.comida_opcion1)
        foodOption2 = findViewById(R.id.comida_opcion2)
        foodOption3 = findViewById(R.id.comida_opcion3)
        foodOption4 = findViewById(R.id.comida_opcion4)

        // Recibir el índice de la pregunta
        currentQuestionIndex = intent.getIntExtra("QUESTION_INDEX", 0)

        if (currentQuestionIndex >= questions.size) {
            finish() // Termina la actividad si no hay más preguntas
            return
        }

        loadQuestion()

        val foodOptions = listOf(foodOption1, foodOption2, foodOption3, foodOption4)
        foodOptions.forEachIndexed { index, imageView ->
            imageView.setOnClickListener { checkAnswer(index) }
        }
    }

    private fun loadQuestion() {
        // Obtiene la pregunta y las opciones correspondientes según el índice actual
        val question = questions[currentQuestionIndex]

        // Asigna la imagen y el texto de la pregunta
        animalImage.setImageResource(question.first) // Establece la imagen del animal
        questionText.text = question.second // Establece el texto de la pregunta

        // Asigna las opciones de comida a las imágenes
        val foodOptions = listOf(foodOption1, foodOption2, foodOption3, foodOption4)
        foodOptions.forEachIndexed { index, imageView ->
            imageView.setImageResource(question.third[index].first) // Establece la imagen de la opción de comida
        }
    }

    private fun checkAnswer(selectedIndex: Int) {
        val explanation = questions[currentQuestionIndex].third[selectedIndex].second
        if (selectedIndex == correctAnswers[currentQuestionIndex]) {
            val intent = Intent(this, RespuestaCorrectaActivity::class.java)
            intent.putExtra("EXPLICACION", explanation)
            intent.putExtra("SIGUIENTE", currentQuestionIndex + 1 < questions.size)
            intent.putExtra("QUESTION_INDEX", currentQuestionIndex + 1)
            startActivity(intent)
        } else {
            val intent = Intent(this, RespuestaIncorrectaActivity::class.java)
            intent.putExtra("EXPLICACION", explanation)
            startActivity(intent)
        }
    }
}