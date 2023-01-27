package com.ror0.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultQuiz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_quiz)

        // here we fetch the views to assign the info we will fetch on the next lines
        val tvName: TextView = findViewById(R.id.tvName)
        val tvScore: TextView = findViewById(R.id.tvScore)
        val btnFinish: Button = findViewById(R.id.tvBtnFinish)

        // here we fetch the info to display in the result activity
        tvName.text = intent.getStringExtra(Constants.USER_NAME) // name of user
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0) // total question of quiz
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0) // number of correct answers from user

        tvScore.text = "Your score is $correctAnswers out of $totalQuestions" // we assign the correct answers and num of questions to the result view

        // we want to be sent back to the main page of the quiz when click on the finish button
        btnFinish.setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java)) // here we return to the mainActivity
        }
    }
}