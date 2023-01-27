package com.ror0.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val editTextName: EditText = findViewById(R.id.editTextName)

        btnStart.setOnClickListener(){
            if(editTextName.text.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                // this enables us to move from the current activity "this" to the "QuizQuestionActivity"
                val intent = Intent(this, QuizQuestionActivity::class.java)
                intent.putExtra(Constants.USER_NAME, editTextName.text.toString()) // sending the name of the user to the constant variable USER_NAME
                startActivity(intent)
            }
        }
    }
}