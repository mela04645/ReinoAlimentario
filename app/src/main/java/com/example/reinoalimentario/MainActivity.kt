package com.example.reinoalimentario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.reinoalimentario.R
import com.example.reinoalimentario.Trivia

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game1Button: ImageView = findViewById(R.id.game1_image)
        val game2Button: ImageView = findViewById(R.id.game2_image)

        game1Button.setOnClickListener {
            startActivity(Intent(this, Quecomelanimal::class.java))
        }

        game2Button.setOnClickListener {
            startActivity(Intent(this, Trivia::class.java))
        }
    }
}
